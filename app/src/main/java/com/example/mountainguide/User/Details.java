package com.example.mountainguide.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mountainguide.*;
import com.example.mountainguide.User.Categories.AllCategories;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Details extends AppCompatActivity {

    TextView t1,t2,t3;
    FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsdesign);

        t1=(TextView)findViewById(R.id.nameholder);
        t2=(TextView)findViewById(R.id.statusholder);
        t3=(TextView)findViewById(R.id.descholder);

        btn=(FloatingActionButton)findViewById(R.id.btnback);

        t1.setText(getIntent().getStringExtra("utitle").toString());
        t2.setText(getIntent().getStringExtra("usubtitle").toString());
        t3.setText(getIntent().getStringExtra("udesc").toString());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                finish();
            }
        });
    }
}
