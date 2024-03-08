package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.beautyhub.asynctasks.NetworkTaskPro;
import com.example.beautyhub.R;

public class CreateAccount_Screen2 extends AppCompatActivity {
    private EditText editTextText11, editTextTextEmailAddress22,editTextTextPassword00,editTextText22,editTextPhone33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_screen2);
        editTextText11 = findViewById(R.id.editTextText11);
        editTextTextEmailAddress22 = findViewById(R.id.editTextTextEmailAddress22);
        editTextTextPassword00 = findViewById(R.id.editTextTextPassword00);
        editTextPhone33 = findViewById(R.id.editTextPhone33);
        editTextText22=findViewById(R.id.editTextText22);
        String[] countryCodes = {"+1", "+61", "+44", "+91"};
        Spinner spinner = findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ImageView backarrow = findViewById(R.id.arrowback6);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccount_Screen2.this, Onboarding_Screen5.class);
                startActivity(intent);
            }

        });
            Button button = findViewById(R.id.button99);
        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            String proName = editTextText11.getText().toString();
            String proEmail = editTextTextEmailAddress22.getText().toString();
            String proPassword = editTextTextPassword00.getText().toString();
            String phoneNumber = editTextPhone33.getText().toString();
            String qualification=editTextText22.getText().toString();


                new NetworkTaskPro().execute(proName, proEmail, proPassword, phoneNumber, qualification);
                }
            });
        }
}