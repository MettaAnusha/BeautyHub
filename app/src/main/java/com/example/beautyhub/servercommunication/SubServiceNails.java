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

public class SubServiceNails {
    private static final String DATA_URL = "http://192.168.150.1/beautyhub/subservicenails.php";


    // Interface for callback methods
    public interface SubServiceNailsResponseListener {
        void onSuccess(List<SubServiceNails> subservicesnails);
        void onError(String error);
    }

    // Method to fetch services from the server
    public static void getSubServicesNails(Context context, final com.example.beautyhub.servercommunication.SubServiceNails.SubServiceNailsResponseListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                DATA_URL,
                null,
                response -> {
//                        Log.d("SubService", "Response received: " + response.toString());
                    List<SubServiceNails> subservicesnailss = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            SubServiceNails subservicenails = new SubServiceNails(
                                    jsonObject.getString("servicenailname"),
                                    decodeBase64(jsonObject.getString("servicenailimage")),
                                    jsonObject.getString("naildescription"),
                                    jsonObject.getString("nailduration"),
                                    jsonObject.getString("nailprice")
                            );
                            subservicesnailss.add(subservicenails);
                        }
                        listener.onSuccess(subservicesnailss); // Notify listener on successful response
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
    private String subserviceNailsName;
    private Bitmap subservicenailsImage;
    private String subserviceNailsdescription;
    private String subserviceNailsduration;
    private String subserviceNailsprice;

    // Constructor
    public SubServiceNails(String subserviceName, Bitmap subserviceImage, String subservicedescription, String subserviceduration, String subserviceprice) {
        this.subserviceNailsName = subserviceName;
        this.subservicenailsImage = subserviceImage;
        this.subserviceNailsdescription = subservicedescription;
        this.subserviceNailsduration = subserviceduration;
        this.subserviceNailsprice = subserviceprice;
    }

    // Getters

    public String getSubServiceNailsName() {
        return subserviceNailsName;
    }

    public Bitmap getSubServiceNailsImage() {
        return subservicenailsImage;
    }

    public String getSubServiceNailsDescription() {
        return subserviceNailsdescription;
    }

    public String getSubServiceNailsDuration() {
        return subserviceNailsduration;
    }

    public String getSubServiceNailsPrice() {
        return subserviceNailsprice;
    }
}

