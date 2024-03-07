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

public class Professionals {

    private static final String DATA_URL = "http://192.168.150.1/beautyhub/professionalslist.php";


    // Interface for callback methods
    public interface ProfessionalsResponseListener {
        void onSuccess(List<Professionals> professional);
        void onError(String error);
    }

    // Method to fetch services from the server
    public static void getSubServices(Context context, final ProfessionalsResponseListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                DATA_URL,
                null,
                response -> {
                    Log.d("Professionals", "Response received: " + response.toString());
                    List<Professionals> professional = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Professionals professionals = new Professionals(
                                    jsonObject.getString("proname"),
                                    decodeBase64(jsonObject.getString("proimage")),
                                    jsonObject.getString("rating"),
                                    jsonObject.getString("profession")



                            );
                            professional.add(professionals);
                        }
                        listener.onSuccess(professional); // Notify listener on successful response
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
    private String proName;
    private Bitmap proImage;
    private String rating;
    private String profession;

    // Constructor
    public Professionals(String proName, Bitmap proImage, String rating, String profession) {
        this.proName = proName;
        this.proImage = proImage;
        this.rating = rating;
        this.profession = profession;
    }

    // Getters

    public String  getProName() {
        return proName;
    }

    public Bitmap getProImage() {
        return proImage;
    }

    public String getRating() {
        return rating;
    }

    public String getProfession() {
        return profession;
    }


}
