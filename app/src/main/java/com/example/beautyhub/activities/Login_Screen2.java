package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.beautyhub.R;

public class Login_Screen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen2);
        String[] countryCodes = {"+1", "+61", "+44", "+91"};
        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Login_Screen2.this, Login_Screen3.class);
                startActivity(intent);
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
}