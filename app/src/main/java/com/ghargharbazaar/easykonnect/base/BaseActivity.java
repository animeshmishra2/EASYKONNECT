package com.ghargharbazaar.easykonnect.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ghargharbazaar.easykonnect.R;

public class BaseActivity extends AppCompatActivity {

    DrawerLayout drawer_layout;
    ImageView drawer_img;
    public FrameLayout content_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_img = (ImageView) findViewById(R.id.drawer_img);
        content_frame = (FrameLayout) findViewById(R.id.content_frame);
        drawer_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                } else {
                    drawer_layout.openDrawer(Gravity.LEFT);
                }
            }
        });
    }
}