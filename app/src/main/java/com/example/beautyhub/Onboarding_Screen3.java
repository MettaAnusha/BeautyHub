package com.example.beautyhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyhub.Onboarding_Screen2;
import com.example.beautyhub.Onboarding_Screen4;
import com.example.beautyhub.R;
import com.example.beautyhub.SubService;

import java.util.List;

public class Onboarding_Screen3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen3);

        ImageView arrow = findViewById(R.id.arrow);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity4
                Intent intent = new Intent(Onboarding_Screen3.this, Onboarding_Screen4.class);
                startActivity(intent);
            }
        });

        ImageView backarrow = findViewById(R.id.backarrow2);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Onboarding_Screen3.this, Onboarding_Screen2.class);
                startActivity(intent);
            }
        });

        // Fetch services from the server
        SubService.getServices(this, new SubService.ServiceResponseListener() {
            @Override
            public void onSuccess(List<SubService> subservices) {
                // Display data for all services
                displayServices(subservices);
            }

            @Override
            public void onError(String error) {
                // Handle error
                Toast.makeText(Onboarding_Screen3.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayServices(List<SubService> services) {
        LinearLayout linearLayout = findViewById(R.id.onboarding2linearlayout);

        // Clear the linear layout before adding new data
        linearLayout.removeAllViews();

        // Inflate and add views for each service
        for (SubService service : services) {
            View serviceView = LayoutInflater.from(this).inflate(R.layout.service_item_layout, linearLayout, false);

            // Find views within the inflated layout
            ImageView imageView = serviceView.findViewById(R.id.serviceimage5);
            TextView serviceNameTextView = serviceView.findViewById(R.id.servicename5);
            TextView servicePriceTextView = serviceView.findViewById(R.id.serviceprice1);

            // Set data to views
            imageView.setImageBitmap(service.getServiceImage());
            serviceNameTextView.setText(service.getSubServiceName());
            servicePriceTextView.setText(service.getPrice());

            // Add the inflated layout to the linear layout
            linearLayout.addView(serviceView);
        }
    }
}
