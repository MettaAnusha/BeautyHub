package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beautyhub.R;
import com.example.beautyhub.asynctasks.NetworkTask1;
import com.example.beautyhub.asynctasks.NetworkTask4;
import com.example.beautyhub.servercommunication.Professionals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Booking_Screen1 extends AppCompatActivity {

    private List<Professionals> professionals = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_screen1);

        Button button = findViewById(R.id.buttonBook);
        CalendarView calendarView = findViewById(R.id.calendarView);
        TextView txtDateTimeSelect = findViewById(R.id.txtDateTimeSelect);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate = txtDateTimeSelect.getText().toString();

                // Store the selected date in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedDate", selectedDate);
                editor.apply();
                Intent intent = new Intent(Booking_Screen1.this, Booking_Screen2.class);
                startActivity(intent);
            }
        });

        ImageView image1 = findViewById(R.id.backArrow);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking_Screen1.this, Onboarding_Screen4.class);
                startActivity(intent);
            }
        });

        // Set current date as default selected date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date(calendarView.getDate()));
        txtDateTimeSelect.setText(currentDate);

        // Set listener for calendar view date change
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Update the text view with selected date
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                String selectedDateString = sdf.format(selectedDate.getTime());
                txtDateTimeSelect.setText(selectedDateString);
            }
        });
    }
}

