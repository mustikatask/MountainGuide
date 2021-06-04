package com.example.mountainguide.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mountainguide.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.Manifest;
import android.content.Intent;
import com.example.mountainguide.User.Location.*;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BasecampDetail extends AppCompatActivity {

    String t1, t2, t3, t4, t5, t6,t7;
    TextView text1, text2, text3, text4, text5;
    FloatingActionButton btn, btncall, btnmaps;
    ImageView img_detail;

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsdesignbasecamp);

        bundle = getIntent().getExtras();

        t1 = bundle.getString("uname");
        t2 = bundle.getString("uaddr");
        t3 = bundle.getString("uticket");
        t4 = bundle.getString("uestimation");
        t5 = bundle.getString("udesc");
        t6 = bundle.getString("uno_telp");
        t7 = bundle.getString("uimgurl");

        text1 = findViewById(R.id.nameholder);
        text2 = findViewById(R.id.addrholder);
        text3 = findViewById(R.id.ticketholder);
        text4 = findViewById(R.id.estimationholder);
        text5 = findViewById(R.id.descholder);
        btn = findViewById(R.id.btnback);
        btncall = findViewById(R.id.call);
        img_detail = findViewById(R.id.img_detail);

        text1.setText(t1);
        text2.setText(t2);
        text3.setText(t3);
        text4.setText(t4);
        text5.setText(t5);
        Glide.with(BasecampDetail.this)
                .load(t7)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(img_detail);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                finish();
            }
        });

        btnmaps = findViewById(R.id.btnback);
        btnmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BasecampLocActivity.class));
                finish();
            }
        });

        btncall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String toCall = "tel:" + t6;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toCall)));
            }
        });


    }
}