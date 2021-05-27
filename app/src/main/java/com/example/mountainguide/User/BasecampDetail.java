package com.example.mountainguide.User;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mountainguide.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BasecampDetail extends AppCompatActivity {

    String t1,t2,t3,t4,t5;
    TextView text1, text2, text3, text4, text5;
    FloatingActionButton btn;

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

        text1 = findViewById(R.id.nameholder);
        text2 = findViewById(R.id.addrholder);
        text3 = findViewById(R.id.ticketholder);
        text4 = findViewById(R.id.estimationholder);
        text5 = findViewById(R.id.descholder);

        text1.setText(t1);
        text2.setText(t2);
        text3.setText(t3);
        text4.setText(t4);
        text5.setText(t5);

        btn = (FloatingActionButton)findViewById(R.id.btnback);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                finish();
            }
        });

    }
}