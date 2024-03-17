package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beautyhub.R;
import com.example.beautyhub.asynctasks.NetworkTask;
import com.example.beautyhub.asynctasks.NetworkTask2;

public class Booking_Screen2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_screen2);
        Button button = findViewById(R.id.button6);
        Button button1 = findViewById(R.id.button11);
        Button button2 = findViewById(R.id.button12);
        Button button3 = findViewById(R.id.button13);
        Button button4 = findViewById(R.id.button14);
        Button button5 = findViewById(R.id.button15);
        Button button6 = findViewById(R.id.button16);
        Button button7 = findViewById(R.id.button17);
        Button button8 =findViewById(R.id.button3);
        TextView textView45=findViewById(R.id.textView45);
        TextView txt=findViewById(R.id.textView46);

        ImageView image = findViewById(R.id.backArrow);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String date = preferences.getString("selectedDate", "");
        String time = preferences.getString("selectedTime", "");
        String proname= preferences.getString("proname","");
        Log.d("proname","pronameinscreen2"+proname);
        String subservicename=preferences.getString("subservicename","");
        String subserviceprice=preferences.getString("subserviceprice","");

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking_Screen2.this, Booking_Screen1.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTime = button.getText().toString();

                // Store the selected date in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedTime", selectedTime);
                editor.apply();
                button.setEnabled(false);
                button.setAlpha(0.5f);

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTime = button1.getText().toString();

                // Store the selected date in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedTime", selectedTime);
                editor.apply();
                button1.setEnabled(false);
                button1.setAlpha(0.5f);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTime = button2.getText().toString();

                // Store the selected date in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedTime", selectedTime);
                editor.apply();
                button2.setEnabled(false);
                button2.setAlpha(0.5f);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTime = button3.getText().toString();

                // Store the selected date in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedTime", selectedTime);
                editor.apply();
                button3.setEnabled(false);
                button3.setAlpha(0.5f);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTime = button4.getText().toString();

                // Store the selected date in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedTime", selectedTime);
                editor.apply();
                button4.setEnabled(false);
                button4.setAlpha(0.5f);

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTime = button5.getText().toString();

                // Store the selected date in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedTime", selectedTime);
                editor.apply();
                button5.setEnabled(false);
                button5.setAlpha(0.5f);

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTime = button6.getText().toString();

                // Store the selected date in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedTime", selectedTime);
                editor.apply();
                button6.setEnabled(false);
                button6.setAlpha(0.5f);

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTime = button7.getText().toString();

                // Store the selected date in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedTime", selectedTime);
                editor.apply();
                button7.setEnabled(false);
                button7.setAlpha(0.5f);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NetworkTask2().execute(date, time, proname, subservicename,subserviceprice);


                Intent intent = new Intent(Booking_Screen2.this, Booking_Screen3.class);
                startActivity(intent);
            }
        });






    }
}