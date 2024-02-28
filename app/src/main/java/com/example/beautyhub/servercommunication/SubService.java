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

public class SubService {

    private static final String DATA_URL = "http://192.168.150.1/beautyhub/subservice.php";


    // Interface for callback methods
    public interface SubServiceResponseListener {
        void onSuccess(List<SubService> subservices);
        void onError(String error);
    }

    // Method to fetch services from the server
    public static void getSubServices(Context context, final SubServiceResponseListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                DATA_URL,
                null,
                response -> {
                    Log.d("SubService", "Response received: " + response.toString());
                    List<SubService> subservices = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            SubService subservice = new SubService(
                                    jsonObject.getString("subservicename"),
                                    decodeBase64(jsonObject.getString("subserviceimage")),
                                    jsonObject.getString("subservicedescription"),
                                    jsonObject.getString("subserviceduration"),
                                    jsonObject.getString("subserviceprice")
                            );
                            subservices.add(subservice);
                        }
                        listener.onSuccess(subservices); // Notify listener on successful response
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
    private String subserviceName;
    private Bitmap subserviceImage;
    private String subservicedescription;
    private String subserviceduration;
    private String subserviceprice;

    // Constructor
    public SubService(String subserviceName, Bitmap subserviceImage, String subservicedescription, String subserviceduration, String subserviceprice) {
        this.subserviceName = subserviceName;
        this.subserviceImage = subserviceImage;
        this.subservicedescription = subservicedescription;
        this.subserviceduration = subserviceduration;
        this.subserviceprice = subserviceprice;
    }

    // Getters

    public String getSubServiceName() {
        return subserviceName;
    }

    public Bitmap getSubServiceImage() {
        return subserviceImage;
    }

    public String getSubServiceDescription() {
        return subservicedescription;
    }

    public String getSubServiceDuration() {
        return subserviceduration;
    }

    public String getSubServicePrice() {
        return subserviceprice;
    }
}
