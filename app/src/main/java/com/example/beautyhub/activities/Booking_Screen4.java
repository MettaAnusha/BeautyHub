//package com.example.beautyhub.activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.example.beautyhub.R;
//import com.example.beautyhub.adapters.AppointmentAdapter;
//import com.example.beautyhub.servercommunication.Appointment;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Booking_Screen4 extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private AppointmentAdapter appointmentAdapter;
//    private List<Appointment> appointments = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_booking_screen4);
//        ImageView image = findViewById(R.id.imageView2);
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Booking_Screen4.this, Booking_Screen3.class);
//                startActivity(intent);
//            }
//        });
//        recyclerView = findViewById(R.id.re);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        appointmentAdapter = new AppointmentAdapter(this, appointments);
//        recyclerView.setAdapter(appointmentAdapter);
//        new FetchServiceDataTask().execute("http://192.168.150.1/beautyhub/appointmentlist.php");
//
//    }
//
//    private class FetchServiceDataTask extends AsyncTask<String, Void, Void> {
//
//        @Override
//        protected Void doInBackground(String... urls) {
//            String urlString = urls[0];
//            HttpURLConnection urlConnection = null;
//            try {
//                URL url = new URL(urlString);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream in = urlConnection.getInputStream();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    stringBuilder.append(line).append("\n");
//                }
//                String jsonString = stringBuilder.toString();
//                JSONObject jsonObject = new JSONObject(jsonString);
//                JSONArray jsonArray = jsonObject.getJSONArray("result");
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject object = jsonArray.getJSONObject(i);
//                    appointments.add(new Appointment(object.getString("proname"), object.getString("subservicename"), object.getString("subserviceprice"), object.getString("date"), object.getString("time")));
//                }
//            } catch (IOException | JSONException e) {
//                e.printStackTrace();
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//        }
//    }
//
//}


package com.example.beautyhub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.example.beautyhub.R;
import com.example.beautyhub.adapters.AppointmentAdapter;
import com.example.beautyhub.asynctasks.NetworkTaskCancel;
import com.example.beautyhub.servercommunication.Appointment;
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

public class Booking_Screen4 extends AppCompatActivity implements AppointmentAdapter.OnCancelButtonClickListener {

    private RecyclerView recyclerView;
    private AppointmentAdapter appointmentAdapter;
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_screen4);

        ImageView image = findViewById(R.id.imageView2);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking_Screen4.this, Booking_Screen3.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.re);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentAdapter = new AppointmentAdapter(this, appointments);
        appointmentAdapter.setOnCancelButtonClickListener(this);
        recyclerView.setAdapter(appointmentAdapter);

        new FetchServiceDataTask().execute("http://192.168.150.1/beautyhub/appointmentlist.php");
    }

    @Override
    public void onCancelButtonClick(Appointment appointment) {

        new NetworkTaskCancel().execute(appointment.getProName(), appointment.getSubServiceName(),appointment.getSubServicePrice(),appointment.getDate(), appointment.getTime());


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
                    appointments.add(new Appointment(object.getString("proname"), object.getString("subservicename"), object.getString("subserviceprice"), object.getString("date"), object.getString("time")));



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
            appointmentAdapter.notifyDataSetChanged(); // Notify adapter after data is loaded
        }
    }
}
