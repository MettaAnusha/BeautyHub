package com.example.beautyhub.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
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

import com.example.beautyhub.asynctasks.NetworkTask;
import com.example.beautyhub.asynctasks.NetworkTask1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beautyhub.R;
import com.example.beautyhub.asynctasks.NetworkTask4;
import com.example.beautyhub.asynctasks.NetworkTaskLogin;
import com.example.beautyhub.servercommunication.Professionals;
import com.example.beautyhub.adapters.ProfessionalsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Onboarding_Screen4 extends AppCompatActivity implements NetworkTask1.TaskCompletionListener {

    private RecyclerView recyclerView;
    private ProfessionalsAdapter professionalsAdapter;
    private List<Professionals> professionals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen4);
        ImageView back=findViewById(R.id.arrowback1);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String selectedServiceName = preferences.getString("selected_service_name", "Default Service Name");
        Log.d("SelectedServiceName", "Selected Service Name: " + selectedServiceName);
        new NetworkTask1(this).execute(selectedServiceName);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Onboarding_Screen4.this,Onboarding_Screen2.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        professionalsAdapter = new ProfessionalsAdapter(this, professionals);
        recyclerView.setAdapter(professionalsAdapter);
    }

    // Implementing the interface method to receive the result from NetworkTask1
    @Override
    public void onTaskCompleted(String result) {
        // Process the result here
        Log.d("Onboarding_Screen4", "Received result: " + result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                if (object.has("proimage")) {
                    Bitmap image = decodeBase64(object.getString("proimage"));
                    professionals.add(new Professionals(object.getString("proname"), image, object.getString("rating"), object.getString("profession")));
                    Log.d("FetchedData", "Professional Name: " + object.getString("proname"));
                    Log.d("FetchedData", "Rating: " + object.getString("rating"));
                    Log.d("FetchedData", "Profession: " + object.getString("profession"));
                }
            }
            professionalsAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Bitmap decodeBase64(String base64String) {
        byte[] decodedBytes = android.util.Base64.decode(base64String, android.util.Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}



