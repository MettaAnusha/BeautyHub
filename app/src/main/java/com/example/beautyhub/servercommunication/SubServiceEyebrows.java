package com.example.beautyhub.servercommunication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubServiceEyebrows {

        private static final String DATA_URL = "http://192.168.150.1/beautyhub/subserviceeyebrows.php";


        // Interface for callback methods
        public interface SubServiceEyebrowsResponseListener {
            void onSuccess(List<com.example.beautyhub.servercommunication.SubServiceEyebrows> subserviceseyebrows);
            void onError(String error);
        }

        // Method to fetch services from the server
        public static void getSubServicesEyebrows(Context context, final com.example.beautyhub.servercommunication.SubServiceEyebrows.SubServiceEyebrowsResponseListener listener) {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    DATA_URL,
                    null,
                    response -> {
//                        Log.d("SubService", "Response received: " + response.toString());
                        List<SubServiceEyebrows> subserviceseyebrowss = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                SubServiceEyebrows subserviceeyebrows = new SubServiceEyebrows(
                                        jsonObject.getString("serviceeyebrowsname"),
                                        decodeBase64(jsonObject.getString("serviceeyebrowsimage")),
                                        jsonObject.getString("eyebrowsdescription"),
                                        jsonObject.getString("eyebrowsduration"),
                                        jsonObject.getString("eyebrowsprice")
                                );
                                subserviceseyebrowss.add(subserviceeyebrows);
                            }
                            listener.onSuccess(subserviceseyebrowss); // Notify listener on successful response
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onError("Error parsing JSON");
                        }
                    },
                    error -> {
                        error.printStackTrace();
                        String errorMessage = error.getMessage(); // Get the error message
                        Log.e("VolleyError", errorMessage); // Log the error message
                        listener.onError("Error fetching data: " + errorMessage); // Pass the error message to the listener
                    }
            );

            // Add the request to the Volley request queue
            Volley.newRequestQueue(context.getApplicationContext()).add(jsonArrayRequest);
        }

        // Method to decode base64 string to Bitmap
        private static Bitmap decodeBase64(String base64String) {
            byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        }

        // Member variables
        private String subserviceEyebrowsName;
        private Bitmap subserviceeyebrowsImage;
        private String subserviceEyebrowsdescription;
        private String subserviceEyebrowsduration;
        private String subserviceEyebrowsprice;

        // Constructor
        public SubServiceEyebrows(String subserviceName, Bitmap subserviceImage, String subservicedescription, String subserviceduration, String subserviceprice) {
            this.subserviceEyebrowsName = subserviceName;
            this.subserviceeyebrowsImage = subserviceImage;
            this.subserviceEyebrowsdescription = subservicedescription;
            this.subserviceEyebrowsduration = subserviceduration;
            this.subserviceEyebrowsprice = subserviceprice;
        }

        // Getters

        public String getSubServiceEyebrowsName() {
            return subserviceEyebrowsName;
        }

        public Bitmap getSubServiceEyebrowsImage() {
            return subserviceeyebrowsImage;
        }

        public String getSubServiceEyebrowsDescription() {
            return subserviceEyebrowsdescription;
        }

        public String getSubServiceEyebrowsDuration() {
            return subserviceEyebrowsduration;
        }

        public String getSubServiceEyebrowsPrice() {
            return subserviceEyebrowsprice;
        }
    }

