package com.example.messenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonActivityPassword;
    private TextView TextViewRegistration;
    private TextView TextViewResetPassword;


    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonActivityPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextLogin.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                // login


            }
        });
        TextViewResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent
            }
        });
        TextViewRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent
            }
        });




    }


    private void initViews(){
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonActivityPassword = findViewById(R.id.buttonActivityPassword);
        TextViewRegistration = findViewById(R.id.TextViewRegistration);
        TextViewResetPassword = findViewById(R.id.TextViewResetPassword);
    }
}