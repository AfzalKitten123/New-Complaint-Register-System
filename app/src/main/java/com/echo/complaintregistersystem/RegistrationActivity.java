package com.echo.complaintregistersystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    EditText nameR,usernameR,passwordR,repasswordR,roomNoR,emailR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        nameR = (EditText)findViewById(R.id.nameR_et);
        usernameR = (EditText)findViewById(R.id.usernameR_et);
        passwordR = (EditText)findViewById(R.id.passwordR_et);
        repasswordR = (EditText)findViewById(R.id.repasswordR_et);

    }
}
