package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyhub.asynctasks.NetworkTaskLogin;
import com.example.beautyhub.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_Screen1 extends AppCompatActivity implements NetworkTaskLogin.OnTaskCompleted {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextPhone;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen1);

        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextNumberPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonLogin = findViewById(R.id.button5);
        String[] countryCodes = {"+1", "+61", "+44", "+91"};
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                Log.d("Login_Screen1", "Email: " + email);
                Log.d("Login_Screen1", "Password: " + password);
                Log.d("Login_Screen1", "Phone: " + phone);



                if (isValidCredentials(email, password, phone)) {
                    NetworkTaskLogin task = new NetworkTaskLogin(Login_Screen1.this); // Pass the interface reference
                    task.execute(email, password, phone);
                } else {
                    Toast.makeText(Login_Screen1.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView arrow = findViewById(R.id.arrowback3);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Screen1.this, Onboarding_Screen5.class);
                startActivity(intent);
            }
        });

        TextView textView = findViewById(R.id.textView19);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Screen1.this, Login_Screen2.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidCredentials(String email, String password, String phone) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false; // Invalid email format
        }

        if (password.isEmpty()) {
            return false; // Password is empty
        }

        if (!android.util.Patterns.PHONE.matcher(phone).matches()) {
            return false; // Invalid phone number format
        }

        return true;
    }

    @Override
    public void onTaskCompleted(String result) {
        Log.d("Login_Screen1", "Server response: " + result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            int success = jsonObject.getInt("success");
            String message = jsonObject.getString("message");

            if (success == 1) {
                // Login successful
                Log.d("Login_Screen1", "Login successful");
                Intent intent = new Intent(Login_Screen1.this, Onboarding_Screen2.class);
                startActivity(intent);
            } else {
                // Login failed
                Log.d("Login_Screen1", "Login failed: " + message);
                Toast.makeText(Login_Screen1.this, message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            // Error parsing JSON response
            Log.e("Login_Screen1", "Error parsing JSON response: " + e.getMessage());
            Toast.makeText(Login_Screen1.this, "An error occurred", Toast.LENGTH_SHORT).show();
        }
    }

}
