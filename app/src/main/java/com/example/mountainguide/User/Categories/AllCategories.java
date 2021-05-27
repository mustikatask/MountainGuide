package com.example.mountainguide.User.Categories;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mountainguide.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.mountainguide.User.*;

public class AllCategories extends AppCompatActivity {

    ImageView backBtn;
    Button btnequip,btnlog,btnguide,btnfaid, btntips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_list);

        //Hooks
        backBtn = findViewById(R.id.back_pressed);
        btnequip = findViewById(R.id.btn_showequip);
        btnguide = findViewById(R.id.showguide);
        btnfaid = findViewById(R.id.showfaid);
        btntips = findViewById(R.id.showtips);



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AllCategories.super.onBackPressed();
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                finish();
            }
        });

        btnequip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Equipment.class));
                finish();
            }
        });

        btnguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HikingGuide.class));
                finish();
            }
        });
        btnfaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FirstAid.class));
                finish();
            }
        });
        btntips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TipsnTrick.class));
                finish();
            }
        });


    }
}