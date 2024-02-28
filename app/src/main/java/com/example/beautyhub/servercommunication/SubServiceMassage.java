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

public class SubServiceMassage {
    private static final String DATA_URL = "http://192.168.150.1/beautyhub/subservicemassage.php";


    // Interface for callback methods
    public interface SubServiceMassageResponseListener {
        void onSuccess(List<SubServiceMassage> subservicesmassage);
        void onError(String error);
    }

    // Method to fetch services from the server
    public static void getSubServicesMassage(Context context, final com.example.beautyhub.servercommunication.SubServiceMassage.SubServiceMassageResponseListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                DATA_URL,
                null,
                response -> {
//                        Log.d("SubService", "Response received: " + response.toString());
                    List<SubServiceMassage> subservicesmassages = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            SubServiceMassage subservicemassage = new SubServiceMassage(
                                    jsonObject.getString("servicemassagename"),
                                    decodeBase64(jsonObject.getString("servicemassageimage")),
                                    jsonObject.getString("massagedescription"),
                                    jsonObject.getString("massageduration"),
                                    jsonObject.getString("massageprice")
                            );
                            subservicesmassages.add(subservicemassage);
                        }
                        listener.onSuccess(subservicesmassages); // Notify listener on successful response
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
    private String subserviceMassageName;
    private Bitmap subservicemassageImage;
    private String subserviceMassagedescription;
    private String subserviceMassageduration;
    private String subserviceMassageprice;

    // Constructor
    public SubServiceMassage(String subserviceName, Bitmap subserviceImage, String subservicedescription, String subserviceduration, String subserviceprice) {
        this.subserviceMassageName = subserviceName;
        this.subservicemassageImage = subserviceImage;
        this.subserviceMassagedescription = subservicedescription;
        this.subserviceMassageduration = subserviceduration;
        this.subserviceMassageprice = subserviceprice;
    }

    // Getters

    public String getSubServiceMassageName() {
        return subserviceMassageName;
    }

    public Bitmap getSubServiceMassageImage() {
        return subservicemassageImage;
    }

    public String getSubServiceMassageDescription() {
        return subserviceMassagedescription;
    }

    public String getSubServiceMassageDuration() {
        return subserviceMassageduration;
    }

    public String getSubServiceMassagePrice() {
        return subserviceMassageprice;
    }
}

