package com.example.beautyhub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubService {

    private static final String DATA_URL = "http://192.168.150.1/beautyhub/subservice.php";

    // Interface for callback methods
    public interface SubServiceResponseListener {
        void onSuccess(List<SubService> subServices);
        void onError(String error);
    }

    // Method to fetch services from the server
    public static void getSubServices(Context context, final SubServiceResponseListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                DATA_URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<SubService> subServices = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                SubService subService = new SubService(
                                        jsonObject.getString("subserviceid"),
                                        jsonObject.getString("subservicename"),
                                        jsonObject.getString("servicename"),
                                        decodeBase64(jsonObject.getString("subserviceimage")),
                                        jsonObject.getString("subservicedescription"),
                                        jsonObject.getString("subserviceduration"),
                                        jsonObject.getString("subserviceprice")
                                );
                                subServices.add(subService);
                            }
                            listener.onSuccess(subServices); // Notify listener on successful response
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onError("Error parsing JSON");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        listener.onError("Error fetching data");
                    }
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
    private String subserviceId;
    private String subserviceName;
    private String serviceName;
    private Bitmap subserviceImage;
    private String subserviceDescription;
    private String subserviceDuration;
    private String subservicePrice;

    // Constructor
    public SubService(String subserviceId, String subserviceName, String serviceName, Bitmap subserviceImage, String subserviceDescription, String subserviceDuration, String subservicePrice) {
        this.subserviceId = subserviceId;
        this.subserviceName = subserviceName;
        this.serviceName = serviceName;
        this.subserviceImage = subserviceImage;
        this.subserviceDescription = subserviceDescription;
        this.subserviceDuration = subserviceDuration;
        this.subservicePrice = subservicePrice;
    }

    // Getters
    public String getSubServiceId() {
        return subserviceId;
    }

    public String getSubServiceName() {
        return subserviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Bitmap getSubServiceImage() {
        return subserviceImage;
    }

    public String getSubServiceDescription() {
        return subserviceDescription;
    }

    public String getSubServiceDuration() {
        return subserviceDuration;
    }

    public String getSubServicePrice() {
        return subservicePrice;
    }
}
