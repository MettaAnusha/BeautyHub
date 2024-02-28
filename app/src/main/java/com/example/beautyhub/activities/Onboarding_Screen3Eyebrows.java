package com.example.beautyhub.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beautyhub.R;
import com.example.beautyhub.servercommunication.SubServiceEyebrows;
import com.example.beautyhub.adapters.SubServiceEyebrowsAdapter;

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

public class Onboarding_Screen3Eyebrows extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SubServiceEyebrowsAdapter subServiceAdapter;
    private List<SubServiceEyebrows> subserviceeyebrows = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen3);
        ImageView back=findViewById(R.id.backarrow2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Onboarding_Screen3Eyebrows.this,Onboarding_Screen2.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subServiceAdapter = new SubServiceEyebrowsAdapter(this, subserviceeyebrows);
        recyclerView.setAdapter(subServiceAdapter);

        new FetchServiceDataTask().execute("http://192.168.150.1/beautyhub/subserviceeyebrows.php");
    }

    private class FetchServiceDataTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {
            String urlString = urls[0];
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                String jsonString = stringBuilder.toString();
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    if (object.has("serviceeyebrowsimage")) {
                        Bitmap image = decodeBase64(object.getString("serviceeyebrowsimage"));
                        subserviceeyebrows.add(new SubServiceEyebrows(object.getString("serviceeyebrowsname"), image, object.getString("eyebrowsdescription"), object.getString("eyebrowsduration"), object.getString("eyebrowsprice")));
                    } else {

                        Log.e(TAG, "Missing 'subserviceeyebrowsimage' key in JSON object at position " + object.toString());
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            subServiceAdapter.notifyDataSetChanged();
        }
    }

    private Bitmap decodeBase64(String base64String) {
        byte[] decodedBytes = android.util.Base64.decode(base64String, android.util.Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
