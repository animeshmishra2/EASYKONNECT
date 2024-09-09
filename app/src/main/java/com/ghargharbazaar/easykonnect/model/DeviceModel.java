package com.ghargharbazaar.easykonnect.model;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class DeviceModel {
    BluetoothDevice bluetoothDevice;
    BluetoothSocket bluetoothSocket;
    int con;

    public DeviceModel(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket,int con) {
        this.bluetoothDevice = bluetoothDevice;
        this.bluetoothSocket = bluetoothSocket;
        this.con = con;
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public BluetoothSocket getBluetoothSocket() {
        return bluetoothSocket;
    }

    public void setBluetoothSocket(BluetoothSocket bluetoothSocket) {
        this.bluetoothSocket = bluetoothSocket;
    }
}
