package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.beautyhub.R;
import com.example.beautyhub.asynctasks.NetworkTaskOTP;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_Screen3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen3);

        EditText ot = findViewById(R.id.editTextNumber4);
        Button button = findViewById(R.id.button8);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered OTP
                String otp = ot.getText().toString();
                Log.d("otp","otp in login screen"+otp);

                // Validate OTP using AsyncTask
                new NetworkTaskOTP(new NetworkTaskOTP.OnTaskCompleted() {
                    @Override
                    public void onTaskCompleted(String response) {
                        try {
                            // Parse the JSON response
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                // OTP is valid, proceed to NewPassword activity
                                Intent intent = new Intent(Login_Screen3.this, NewPassword.class);
                                startActivity(intent);
                            } else {
                                // OTP is invalid, display an error message
                                Toast.makeText(Login_Screen3.this, "Invalid OTP, please try again", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).execute(otp);
            }
        });

        ImageView arrow = findViewById(R.id.arrowback4);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Login_Screen2 activity
                Intent intent = new Intent(Login_Screen3.this, Login_Screen2.class);
                startActivity(intent);
            }
        });
    }
}
