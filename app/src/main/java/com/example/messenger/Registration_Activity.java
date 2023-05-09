package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration_Activity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private Button ButtonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =  getTrimValue(editTextLogin);
                String password =  getTrimValue(editTextPassword);
                String name =  getTrimValue(editTextName);
                String lastName =  getTrimValue(editTextLastName);
                int age = Integer.parseInt(getTrimValue(editTextAge) );
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
}