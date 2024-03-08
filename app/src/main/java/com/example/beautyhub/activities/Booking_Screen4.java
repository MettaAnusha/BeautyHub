package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.beautyhub.R;

public class Booking_Screen4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_screen4);
        ImageView image=findViewById(R.id.backArrow);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking_Screen4.this, Booking_Screen3.class);
                startActivity(intent);
            }
        });
    }
}