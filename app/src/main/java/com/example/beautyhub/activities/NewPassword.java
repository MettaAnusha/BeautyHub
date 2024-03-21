package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautyhub.R;
import com.example.beautyhub.asynctasks.NetworkTaskAddPassword;

public class NewPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        EditText pass = findViewById(R.id.password);
        EditText confirmpass = findViewById(R.id.confirmpassword);


        Button button = findViewById(R.id.button20);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String phonenumber = preferences.getString("phonenumber", "");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String custpassword = pass.getText().toString();
                String confirmpassword = confirmpass.getText().toString();
                if (!custpassword.equals(confirmpassword)) {
                    String msg = "Please enter valid password, Passwords don't match";
                    Toast.makeText(NewPassword.this, msg, Toast.LENGTH_SHORT).show();
                } else {

                    new NetworkTaskAddPassword().execute(custpassword,phonenumber);
                    Log.d("custpassword","custpassword"+custpassword);
                    Log.d("phonenumber","phonenumber"+phonenumber);
                    String msg1 = "Password changed successfully, Please Login now!";

                    Toast.makeText(NewPassword.this, msg1, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewPassword.this, Login_Screen1.class);
                    startActivity(intent);
                }
            }
        });
    }
}
