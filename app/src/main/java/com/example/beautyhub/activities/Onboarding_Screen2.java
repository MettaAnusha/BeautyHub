package com.example.beautyhub.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beautyhub.R;
import com.example.beautyhub.servercommunication.Service;

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

public class Onboarding_Screen2 extends AppCompatActivity {

    private List<Service> serviceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen2);
        Button button2 = findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity2
                Intent intent = new Intent(Onboarding_Screen2.this,Onboarding_Screen3.class);
                startActivity(intent);
            }
        });

        // Fetch service data from the API
        new FetchServiceDataTask().execute("http://192.168.150.1/beautyhub/service.php");
    }

    private void populateViews() {
        if (serviceList.size() >= 4) {
            Service service1 = serviceList.get(0);
            Service service2 = serviceList.get(1);
            Service service3 = serviceList.get(2);
            Service service4 = serviceList.get(3);

            // Bind service images and names to views
            ImageView serviceImage1 = findViewById(R.id.serviceimage1);
            TextView serviceName1 = findViewById(R.id.servicename1);
            serviceImage1.setImageBitmap(service1.getServiceImage());
            serviceName1.setText(service1.getServiceName());

            ImageView serviceImage2 = findViewById(R.id.serviceimage2);
            TextView serviceName2 = findViewById(R.id.servicename2);
            serviceImage2.setImageBitmap(service2.getServiceImage());
            serviceName2.setText(service2.getServiceName());

            ImageView serviceImage3 = findViewById(R.id.serviceimage3);
            TextView serviceName3 = findViewById(R.id.servicename3);
            serviceImage3.setImageBitmap(service3.getServiceImage());
            serviceName3.setText(service3.getServiceName());

            ImageView serviceImage4 = findViewById(R.id.serviceimage4);
            TextView serviceName4 = findViewById(R.id.servicename4);
            serviceImage4.setImageBitmap(service4.getServiceImage());
            serviceName4.setText(service4.getServiceName());
        }
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
                    // Decode base64 image string to Bitmap
                    Bitmap image = decodeBase64(object.getString("serviceimage"));
                    String serviceName = object.getString("servicename");
                    serviceList.add(new Service(object.getString("serviceid"), serviceName, image, object.getString("description"), object.getString("duration"), object.getString("price")));
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
            populateViews();
        }
    }

    // Method to decode base64 string to Bitmap
    private Bitmap decodeBase64(String base64String) {
        byte[] decodedBytes = android.util.Base64.decode(base64String, android.util.Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
