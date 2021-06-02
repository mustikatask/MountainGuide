package com.example.mountainguide.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mountainguide.*;
import com.example.mountainguide.User.Categories.AllCategories;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Details extends AppCompatActivity {

    String t1,t2,t3,t4;
    TextView text1, text2, text3, text4;
    FloatingActionButton btn;
    ImageView img_detail;

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsdesign);

        bundle = getIntent().getExtras();
        t1 = bundle.getString("utitle");
        t2 = bundle.getString("usubtitle");
        t3 = bundle.getString("udesc");
        t4 = bundle.getString("uimgurl");

        text1=findViewById(R.id.nameholder);
        text2=findViewById(R.id.statusholder);
        text3=findViewById(R.id.descholder);
        img_detail = findViewById(R.id.img_detail);
        btn=findViewById(R.id.btnback);

        text1.setText(t1);
        text2.setText(t2);
        text3.setText(t3);
        Glide.with(Details.this)
                .load(t4)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(img_detail);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                finish();
            }
        });
    }
}
