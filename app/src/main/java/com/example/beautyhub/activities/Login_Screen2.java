package com.example.beautyhub.activities;//package com.example.beautyhub.activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Spinner;
//
//import com.example.beautyhub.R;
//
//public class Login_Screen2 extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_screen2);
//        String[] countryCodes = {"+1", "+61", "+44", "+91"};
//        Spinner spinner = findViewById(R.id.spinner2);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryCodes);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//
//        Button button = findViewById(R.id.button7);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start Activity2
//                Intent intent = new Intent(Login_Screen2.this, Login_Screen3.class);
//                startActivity(intent);
//            }
//        });
//        ImageView arrow = findViewById(R.id.arrowback2);
//        arrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start Activity2
//                Intent intent = new Intent(Login_Screen2.this, Login_Screen1.class);
//                startActivity(intent);
//            }
//        });
//    }
//}
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.beautyhub.R;
import com.example.beautyhub.activities.Login_Screen1;
import com.example.beautyhub.activities.Login_Screen3;

public class Login_Screen2 extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen2);

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

    private void setImageBasedOnCountryCode(String countryCode) {
        switch (countryCode) {
            case "+1":
                imageView.setImageResource(R.drawable.canada);
                break;
            case "+61":
                imageView.setImageResource(R.drawable.uk);
                break;
            case "+44":
                imageView.setImageResource(R.drawable.australia);
                break;
            case "+91":
                imageView.setImageResource(R.drawable.india);
                break;
            default:
                imageView.setImageResource(R.drawable.canada);
                break;
        }
    }
}
