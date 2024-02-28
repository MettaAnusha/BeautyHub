package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beautyhub.asynctasks.NetworkTask;
import com.example.beautyhub.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateAccount_Screen1 extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_screen1);
        ImageView backarrow = findViewById(R.id.arrowback5);
        editTextName = findViewById(R.id.editTextText);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress2);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        editTextPhone = findViewById(R.id.editTextPhone3);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(CreateAccount_Screen1.this, Onboarding_Screen5.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String custName = editTextName.getText().toString();
                String custEmail = editTextEmail.getText().toString();
                String custPassword = editTextPassword.getText().toString();
                String phoneNumber = editTextPhone.getText().toString();


                new NetworkTask().execute(custName, custEmail, custPassword, phoneNumber);

            }
        });





    }
}