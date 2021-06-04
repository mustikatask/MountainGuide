package com.example.mountainguide.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mountainguide.*;
import com.example.mountainguide.User.UserDashboard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    private FirebaseUser User;
    private DatabaseReference reference;
    private String userID;

    TextView name, mail;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);


        name = findViewById(R.id.username);
        mail = findViewById(R.id.email);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
//                Toast.makeText(Profile.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Profile.this, UserDashboard.class));
//                finish();
            }
        });

        User = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = User.getUid();

        final TextView nameTextView = findViewById(R.id.username);
        final TextView emailTextView = findViewById(R.id.email);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if(user != null) {
                    String email = user.email;
                    String username = user.username;

                    emailTextView.setText(email);
                    nameTextView.setText(username);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Something wrong happened! ", Toast.LENGTH_LONG).show();
            }
        });
    }
}