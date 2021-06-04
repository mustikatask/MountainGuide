package com.example.mountainguide.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mountainguide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.ims.RegistrationManager;
import android.util.Patterns;
import android.view.View;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mountainguide.User.*;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private TextView banner;
    private Button registerUser;
    private FirebaseAuth mAuth;
    private TextInputEditText editTextUsername, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_sign_up);

        mAuth = FirebaseAuth.getInstance();
        banner = findViewById(R.id.signup_title_text);
        banner.setOnClickListener(this);

        registerUser = findViewById(R.id.registerButton);
        registerUser.setOnClickListener(this);

        editTextUsername = findViewById(R.id.uname);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);

        progressBar = findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.registerButton:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();

        if (username.isEmpty()){
            editTextUsername.setError("Username is required!");
            editTextUsername.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            editTextPassword.setError("Min password length should be 6 character!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(username, email);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUp.this,"User has been registered succesfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        //redirect to Login Layout
                                    }else {
                                        Toast.makeText(SignUp.this, "Failed to register! Try Again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SignUp.this, "Failed to register! Try Again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}