package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.beautyhub.R;
import com.example.beautyhub.asynctasks.NetworkTask2;
import com.example.beautyhub.asynctasks.NetworkTaskForgotPassword;


import java.util.Random;

public class Login_Screen2 extends AppCompatActivity {

    private ImageView imageView;

    private String phonenumber; // Declare phoneNumber as a class-level variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen2);
        EditText phoneEditText = findViewById(R.id.editTextPhone2);


        String[] countryCodes = {"+1", "+61", "+44", "+91"};
        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition("+1"));
        imageView = findViewById(R.id.imageView);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                setImageBasedOnCountryCode(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        Button button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonenumber = phoneEditText.getText().toString().trim();
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("phonenumber", phonenumber);
                editor.apply();


                new NetworkTaskForgotPassword(new NetworkTaskForgotPassword.AsyncResponse() {
                    @Override
                    public void processFinish(String response) {
                        if (response.equals("OTP sent successfully!")) {
                            // Phone number is valid, proceed to Login_Screen3
                            Intent intent = new Intent(Login_Screen2.this, Login_Screen3.class);
                            startActivity(intent);
                        } else {
                            // Invalid response or error occurred
                            Toast.makeText(Login_Screen2.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }).execute(phonenumber);

            }
        });


        ImageView arrow = findViewById(R.id.arrowback2);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Login_Screen2.this, Login_Screen1.class);
                startActivity(intent);
            }
        });
    }

    private void setImageBasedOnCountryCode(String countryCode) {
        // Your existing code here
    }
}
