package com.example.beautyhub.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beautyhub.R;
import com.example.beautyhub.adapters.ProfessionalsAdapter;
import com.example.beautyhub.asynctasks.NetworkTask1;
import com.example.beautyhub.asynctasks.NetworkTask2;
import com.example.beautyhub.asynctasks.NetworkTask3;
import com.example.beautyhub.servercommunication.Professionals;
import com.example.beautyhub.servercommunication.SubService;
import com.example.beautyhub.adapters.SubServiceAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Onboarding_Screen3 extends AppCompatActivity implements NetworkTask3.TaskCompletionListener {

    private RecyclerView recyclerView;
    private SubServiceAdapter subServiceAdapter;
    private List<SubService> subservice = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen3);
        ImageView back=findViewById(R.id.backarrow2);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String servicename = preferences.getString("selected_service_name", "Default Service Name");
        Log.d("SelectedServiceName", "Selected Service Name: " + servicename);
        new NetworkTask3(this).execute(servicename);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Onboarding_Screen3.this,Onboarding_Screen2.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subServiceAdapter = new SubServiceAdapter(this, subservice);
        recyclerView.setAdapter(subServiceAdapter);
    }

    // Implementing the interface method to receive the result from NetworkTask1
    @Override
    public void onTaskCompleted(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                if (object.has("subserviceimage")) {
                    Bitmap image = decodeBase64(object.getString("subserviceimage"));
                    subservice.add(new SubService(object.getString("subservicename"), image, object.getString("subservicedescription"), object.getString("subserviceduration"), object.getString("subserviceprice")));

                }
            }
            subServiceAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap decodeBase64(String base64String) {
        byte[] decodedBytes = android.util.Base64.decode(base64String, android.util.Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
