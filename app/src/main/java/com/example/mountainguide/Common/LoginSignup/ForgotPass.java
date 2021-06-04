package com.example.mountainguide.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mountainguide.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ForgotPass extends AppCompatActivity {
    private TextInputEditText emailEditText;
    private Button resetPassBtn;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgor_pass);

        emailEditText = findViewById(R.id.email);
        resetPassBtn = findViewById(R.id.resetPass);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        resetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

    }
    private void resetPassword(){
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()){
            emailEditText.setError("Email is required!");
            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please provide valid email!");
            emailEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPass.this, "Check Your email to reset your password!", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(ForgotPass.this, "Try again! Something wrong happened", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}