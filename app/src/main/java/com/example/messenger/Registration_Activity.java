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

import com.google.firebase.auth.FirebaseUser;

public class Registration_Activity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private Button ButtonRegister;

    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        observeViewModel();
        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =  getTrimValue(editTextLogin);
                String password =  getTrimValue(editTextPassword);
                String name =  getTrimValue(editTextName);
                String lastName =  getTrimValue(editTextLastName);
                int age = Integer.parseInt(getTrimValue(editTextAge) );
                viewModel.signUp(email,password,name,lastName,age);
            }
        });
    }

    private void observeViewModel(){
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null){
                    Toast.makeText(Registration_Activity.this,errorMessage, Toast.LENGTH_SHORT).show();
                }

            }
        });
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    Intent intent = UsersActivity.newIntent(Registration_Activity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void initViews(){
     editTextLogin = findViewById(R.id.editTextLogin);
     editTextPassword = findViewById(R.id.editTextPassword);
     editTextName = findViewById(R.id.editTextName);
     editTextLastName = findViewById(R.id.editTextLastName);
     editTextAge = findViewById(R.id.editTextAge);
     ButtonRegister = findViewById(R.id.ButtonRegister);

    }

    private String getTrimValue (EditText editText){
        return editText.getText().toString().trim();
    }


    public static Intent newIntent(Context context){
        return  new Intent(context, Registration_Activity.class);
    }
}