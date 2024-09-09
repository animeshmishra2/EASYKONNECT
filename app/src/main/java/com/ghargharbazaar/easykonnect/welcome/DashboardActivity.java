package com.ghargharbazaar.easykonnect.welcome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ghargharbazaar.easykonnect.R;
import com.ghargharbazaar.easykonnect.base.BaseActivity;
import com.ghargharbazaar.easykonnect.utils.UserDetails;

public class DashboardActivity extends BaseActivity {

    UserDetails userDetails;
    CardView new_order, hold_orders, counter_orders, online_orders, request_to_wh, received_request, close_counter, logout, open_counter, logout_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_dashboard, content_frame);
        userDetails = new UserDetails(getApplicationContext());
        new_order = (CardView) findViewById(R.id.new_order);
        hold_orders = (CardView) findViewById(R.id.hold_orders);
        open_counter = (CardView) findViewById(R.id.open_counter);
        logout_close = (CardView) findViewById(R.id.logout_close);
        counter_orders = (CardView) findViewById(R.id.counter_orders);
        online_orders = (CardView) findViewById(R.id.online_orders);
        request_to_wh = (CardView) findViewById(R.id.request_to_wh);
        received_request = (CardView) findViewById(R.id.received_request);
        close_counter = (CardView) findViewById(R.id.close_counter);
        logout = (CardView) findViewById(R.id.logout);
        new_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewOrderActivity.class);
                startActivity(intent);
            }
        });
        close_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CloseCounterActivity.class);
                startActivityForResult(intent, 29);
            }
        });
        logout_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetails.clearAll();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetails.clearAll();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        open_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OpenCounterActivity.class);
                startActivityForResult(intent, 29);
            }
        });
        setData();
    }

    public void setData() {
        if (userDetails.getIdcounter().equalsIgnoreCase("0")) {
            open_counter.setVisibility(View.VISIBLE);
            logout_close.setVisibility(View.VISIBLE);
            new_order.setVisibility(View.GONE);
            hold_orders.setVisibility(View.GONE);
            counter_orders.setVisibility(View.GONE);
            online_orders.setVisibility(View.GONE);
            close_counter.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
        } else {
            open_counter.setVisibility(View.GONE);
            logout_close.setVisibility(View.GONE);
            new_order.setVisibility(View.VISIBLE);
            hold_orders.setVisibility(View.VISIBLE);
            counter_orders.setVisibility(View.VISIBLE);
            online_orders.setVisibility(View.VISIBLE);
            close_counter.setVisibility(View.VISIBLE);
            logout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 29 && resultCode == RESULT_OK) {
            setData();
        }
    }
}