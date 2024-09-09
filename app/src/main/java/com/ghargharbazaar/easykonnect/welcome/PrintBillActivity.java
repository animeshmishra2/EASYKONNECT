package com.ghargharbazaar.easykonnect.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghargharbazaar.easykonnect.R;
import com.ghargharbazaar.easykonnect.model.DeviceModel;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class PrintBillActivity extends AppCompatActivity {

    ImageView back_button;
    LinearLayout no_products;
    RecyclerView recyclerView;
    ArrayList<DeviceModel> deviceModels;
    CustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_bill);
        back_button = (ImageView) findViewById(R.id.back_button);
        no_products = (LinearLayout) findViewById(R.id.no_products);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        deviceModels = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new CustomerAdapter();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // If permissions are not granted, request them
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        } else {
            // If permissions are already granted, proceed to get the list of connected devices
            getConnectedBluetoothDevices();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 123) {
            // Check if all permissions are granted
            boolean allPermissionsGranted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

            if (allPermissionsGranted) {
                // If permissions are granted, get the list of connected devices
                getConnectedBluetoothDevices();
            } else {
                // If permissions are not granted, show a toast indicating the failure
                Toast.makeText(this, "Bluetooth permissions required", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getConnectedBluetoothDevices() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_SHORT).show();
            return;
        }
        @SuppressLint("MissingPermission") Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        deviceModels.clear();
        for (BluetoothDevice device : pairedDevices) {
            isConnectedBluetooth(device);
            @SuppressLint("MissingPermission") String deviceName = device.getName();
            String deviceAddress = device.getAddress();
            System.out.println("Animesh " + deviceAddress + " --- " + deviceName + " --- ");
        }
        if (deviceModels.size() > 0) {
            no_products.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(adapter);
        } else {
            no_products.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @SuppressLint("MissingPermission")
    private int isConnectedBluetooth(BluetoothDevice bluetoothDevice) {
        int pos = 0;
        BluetoothSocket bluetoothSocket = null;
        for (ParcelUuid uuid : bluetoothDevice.getUuids()) {
            System.out.println("Animesh " + uuid.getUuid().toString());
            try {
                bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid.getUuid());
                //bluetoothSocket.connect();
                if (bluetoothSocket.isConnected()) {
                    pos = 1;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        deviceModels.add(new DeviceModel(bluetoothDevice, bluetoothSocket, pos));
        return pos;
    }

    class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.TypeHolder> {


        @Override
        public int getItemCount() {
            return deviceModels.size();

        }

        @SuppressLint("MissingPermission")
        @Override
        public void onBindViewHolder(CustomerAdapter.TypeHolder holder, @SuppressLint("RecyclerView") final int position) {
            holder.prod_name.setText(deviceModels.get(position).getBluetoothDevice().getName());
            if (deviceModels.get(position).getCon() == 0) {
                holder.connect_device.setText("Connect");
            } else {
                holder.connect_device.setText("Print");
            }
            holder.prod_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    connectToDevice(deviceModels.get(position).getBluetoothDevice(), position);
                   /* bluetoothDevice = bluetoothAdapter.getRemoteDevice(bluetoothDevices.get(position).getAddress());
                    connectToDevice();*/
                }
            });
        }

        @Override
        public CustomerAdapter.TypeHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
            ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.device_row, viewGroup, false);
            CustomerAdapter.TypeHolder listHolder = new CustomerAdapter.TypeHolder(mainGroup);
            return listHolder;
        }

        public class TypeHolder extends RecyclerView.ViewHolder {

            TextView prod_name, connect_device;
            ImageView pr_image;
            LinearLayout prod_layout;

            public TypeHolder(View view) {
                super(view);
                prod_layout = (LinearLayout) view.findViewById(R.id.prod_layout);
                prod_name = (TextView) view.findViewById(R.id.prod_name);
                connect_device = (TextView) view.findViewById(R.id.connect_device);
                pr_image = (ImageView) view.findViewById(R.id.pr_image);
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void connectToDevice(BluetoothDevice bluetoothDevice, int poss) {
        try {
            // Use a well-known UUID for the Bluetooth service you want to connect to
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            @SuppressLint("MissingPermission") BluetoothSocket bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
            bluetoothSocket.connect();
            if (bluetoothSocket.isConnected()) {
                System.out.println("Animesh connected successfully!");
                deviceModels.get(poss).setBluetoothSocket(bluetoothSocket);
                deviceModels.get(poss).setCon(1);
                OutputStream outputStream = bluetoothSocket.getOutputStream();
                outputStream.write(getIntent().getExtras().getString("ST").getBytes());
                outputStream.flush();
                outputStream.close();
                bluetoothSocket.close();
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}