package com.example.beautyhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

//        ImageView arrow = findViewById(R.id.arrow);
//        arrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start Activity4
//                Intent intent = new Intent(Onboarding_Screen3.this, Onboarding_Screen4.class);
//                startActivity(intent);
//            }
//        });

        ImageView backarrow = findViewById(R.id.backarrow2);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Onboarding_Screen3.this, Onboarding_Screen2.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SubService.getSubServices(this, new SubService.SubServiceResponseListener() {
            @Override
            public void onSuccess(List<SubService> subServices) {
                SubServiceAdapter adapter = new SubServiceAdapter(Onboarding_Screen3.this, subServices);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(Onboarding_Screen3.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
