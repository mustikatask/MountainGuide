package com.example.mountainguide.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mountainguide.*;
import com.example.mountainguide.User.UserDashboard;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    TextView name, mail;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        logout = findViewById(R.id.logout);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);


        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Profile.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Profile.this, UserDashboard.class));
                finish();
            }
        });
    }
}