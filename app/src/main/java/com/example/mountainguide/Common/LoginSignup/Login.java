package com.example.mountainguide.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mountainguide.User.*;

import com.example.mountainguide.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button register, login, forgotPass;
    private TextInputEditText editTextEmail, editTextPassword;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);

        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        login = findViewById(R.id.login);
        login.setOnClickListener(this);

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);

        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        forgotPass = findViewById(R.id.forgotpass);
        forgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, SignUp.class));
                break;
            case R.id.login:
                userLogin();
                break;
            case R.id.forgotpass:
                startActivity(new Intent(this, ForgotPass.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is Required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter valid email!");
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length()<6){
            editTextPassword.setError("Min password length is 6 character!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user.isEmailVerified()){
                                startActivity(new Intent(Login.this,Profile.class ));
                            }else{
                                user.sendEmailVerification();
                                Toast.makeText(Login.this, "Check Your emaul to verify your account!", Toast.LENGTH_LONG).show();
                            }
                            //redirect to user profile

                        }else {
                            Toast.makeText(Login.this, "Failed to login ! Please check your credentials", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}