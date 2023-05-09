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
import android.widget.Toast;

public class Reset_password_activity extends AppCompatActivity {

    private static final String EXTRA_EMAIL = "email";
    private Button buttonResetPassword;
    private EditText editTextLogin;

    private ResetPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initViews();

        viewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);
        observeViewModel();
        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        editTextLogin.setText(email);
        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getTrimValue(editTextLogin);
                viewModel.resetPassword(email);
            }
        });
    }
    private void observeViewModel(){
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null){
                    Toast.makeText(Reset_password_activity.this,errorMessage, Toast.LENGTH_SHORT).show();
                }

            }
        });
        viewModel.getSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success){
                    Toast.makeText(Reset_password_activity.this,"Ссылка для сброса пароля отправлена на почту", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getTrimValue (EditText editText){
        return editText.getText().toString().trim();
    }



    private void initViews(){
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
        editTextLogin = findViewById(R.id.editTextLogin);

    }
    public static Intent newIntent(Context context, String email){
        Intent intent = new Intent(context, Reset_password_activity.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }
}