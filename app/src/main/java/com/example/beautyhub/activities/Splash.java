package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.beautyhub.R;

public class Splash extends AppCompatActivity {
    private static final long SPLASH_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        // Using a Handler to delay the start of MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start MainActivity
                Intent intent = new Intent(Splash.this, Onboarding_Screen1.class);
                startActivity(intent);
                finish(); // Finish the splash activity
            }
        }, SPLASH_DELAY);
    }
}