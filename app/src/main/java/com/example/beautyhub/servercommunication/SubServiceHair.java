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

public class SubServiceHair {
    private static final String DATA_URL = "http://192.168.150.1/beautyhub/subservicehair.php";


    // Interface for callback methods
    public interface SubServiceHairResponseListener {
        void onSuccess(List<SubServiceHair> subserviceshair);
        void onError(String error);
    }

    // Method to fetch services from the server
    public static void getSubServicesHair(Context context, final com.example.beautyhub.servercommunication.SubServiceHair.SubServiceHairResponseListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                DATA_URL,
                null,
                response -> {
//                        Log.d("SubService", "Response received: " + response.toString());
                    List<SubServiceHair> subserviceshairs = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            SubServiceHair subservicehair = new SubServiceHair(
                                    jsonObject.getString("servicehairname"),
                                    decodeBase64(jsonObject.getString("servicehairimage")),
                                    jsonObject.getString("hairdescription"),
                                    jsonObject.getString("hairduration"),
                                    jsonObject.getString("hairprice")
                            );
                            subserviceshairs.add(subservicehair);
                        }
                        listener.onSuccess(subserviceshairs); // Notify listener on successful response
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
    private String subserviceHairName;
    private Bitmap subservicehairImage;
    private String subserviceHairdescription;
    private String subserviceHairduration;
    private String subserviceHairprice;

    // Constructor
    public SubServiceHair(String subserviceName, Bitmap subserviceImage, String subservicedescription, String subserviceduration, String subserviceprice) {
        this.subserviceHairName = subserviceName;
        this.subservicehairImage = subserviceImage;
        this.subserviceHairdescription = subservicedescription;
        this.subserviceHairduration = subserviceduration;
        this.subserviceHairprice = subserviceprice;
    }

    // Getters

    public String getSubServiceHairName() {
        return subserviceHairName;
    }

    public Bitmap getSubServiceHairImage() {
        return subservicehairImage;
    }

    public String getSubServiceHairDescription() {
        return subserviceHairdescription;
    }

    public String getSubServiceHairDuration() {
        return subserviceHairduration;
    }

    public String getSubServiceHairPrice() {
        return subserviceHairprice;
    }
}

