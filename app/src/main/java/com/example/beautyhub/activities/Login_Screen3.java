package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.beautyhub.R;

public class Login_Screen3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen3);

        Button button = findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Login_Screen3.this, Login_Screen2.class);
                startActivity(intent);
            }
        });
        ImageView arrow = findViewById(R.id.arrowback4);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Login_Screen3.this, Login_Screen2.class);
                startActivity(intent);
            }
        });
    }
}