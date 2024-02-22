package com.example.beautyhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Login_Screen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen1);
//        Button button5 = findViewById(R.id.button5);
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start Activity2
//                Intent intent = new Intent(Login_Screen1.this, Login_Screen2.class);
//                startActivity(intent);
//            }
//        });
        ImageView arrow = findViewById(R.id.arrowback3);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Login_Screen1.this, Onboarding_Screen5.class);
                startActivity(intent);
            }
        });
        TextView textView =findViewById(R.id.textView19);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Login_Screen1.this, Login_Screen2.class);
                startActivity(intent);
            }
        });
    }
}