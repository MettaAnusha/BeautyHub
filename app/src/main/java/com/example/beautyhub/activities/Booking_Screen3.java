package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beautyhub.R;

public class Booking_Screen3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_screen3);
        TextView txtBookingDetails = findViewById(R.id.txtBookingDetails);
        TextView textView11 = findViewById(R.id.textView11);
        TextView textView42 = findViewById(R.id.textView42);
        TextView textView = findViewById(R.id.txtSubHeader);
        TextView textView40 = findViewById(R.id.textView40);


        Button button = findViewById(R.id.buttonBook);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String date = preferences.getString("selectedDate", "");
        String time = preferences.getString("selectedTime", "");
        String proname=preferences.getString("proname", "");
        String subservicename=preferences.getString("subservicename","");
        String subserviceprice=preferences.getString("subserviceprice","");


        // Set selected date and time to respective TextViews
        txtBookingDetails.setText(date);
        textView11.setText(time);
        textView42.setText(proname);
       textView.setText(subservicename);
        textView40.setText(subserviceprice);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking_Screen3.this, Onboarding_Screen2.class);
                startActivity(intent);
            }
        });

        Button button18 = findViewById(R.id.button18);
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking_Screen3.this, Booking_Screen4.class);
                startActivity(intent);
            }
        });

    }
}