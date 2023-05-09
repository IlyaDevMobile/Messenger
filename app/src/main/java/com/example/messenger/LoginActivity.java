package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";
    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonActivityPassword;
    private TextView TextViewRegistration;
    private TextView TextViewResetPassword;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        observeViewModel();
        setupClickListeners();



    }

    private void observeViewModel() {
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null) {
                    Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                   Intent intent = UsersActivity.newIntent(LoginActivity.this);
                   startActivity(intent);
                   finish();
                }
            }
        });
    }


    private void initViews() {
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonActivityPassword = findViewById(R.id.buttonActivityPassword);
        TextViewRegistration = findViewById(R.id.TextViewRegistration);
        TextViewResetPassword = findViewById(R.id.TextViewResetPassword);
    }

    private void setupClickListeners(){
        buttonActivityPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextLogin.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                viewModel.login(email, password);

            }
        });
        TextViewResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Reset_password_activity.newIntent(
                        LoginActivity.this, editTextLogin.getText().toString().trim());
                startActivity(intent);
            }
        });
        TextViewRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Registration_Activity.newIntent(LoginActivity.this);
                startActivity(intent);
            }
        });

    }

    public static  Intent newIntent(Context context){
        return new Intent(context, LoginActivity.class);
    }
}