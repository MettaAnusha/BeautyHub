package com.example.beautyhub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

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

public class Service {

        private static final String DATA_URL = "http://192.168.150.1/beautyhub/service.php";

        // Interface for callback methods
        public interface ServiceResponseListener {
                void onSuccess(List<Service> services);
                void onError(String error);
        }

        // Method to fetch services from the server
        public static void getServices(Context context, final ServiceResponseListener listener) {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        DATA_URL,
                        null,
                        new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                        List<Service> services = new ArrayList<>();
                                        try {
                                                for (int i = 0; i < response.length(); i++) {
                                                        JSONObject jsonObject = response.getJSONObject(i);
                                                        Service service = new Service(
                                                                jsonObject.getString("serviceid"),
                                                                jsonObject.getString("servicename"),
                                                                decodeBase64(jsonObject.getString("serviceimage")),
                                                                jsonObject.getString("description"),
                                                                jsonObject.getString("duration"),
                                                                jsonObject.getString("price")
                                                        );
                                                        services.add(service);
                                                }
                                                listener.onSuccess(services); // Notify listener on successful response
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
        private String serviceId;
        private String serviceName;
        private Bitmap serviceImage;
        private String description;
        private String duration;
        private String price;

        // Constructor
        public Service(String serviceId, String serviceName, Bitmap serviceImage, String description, String duration, String price) {
                this.serviceId = serviceId;
                this.serviceName = serviceName;
                this.serviceImage = serviceImage;
                this.description = description;
                this.duration = duration;
                this.price = price;
        }

        // Getters
        public String getServiceId() {
                return serviceId;
        }

        public String getServiceName() {
                return serviceName;
        }

        public Bitmap getServiceImage() {
                return serviceImage;
        }

        public String getDescription() {
                return description;
        }

        public String getDuration() {
                return duration;
        }

        public String getPrice() {
                return price;
        }
}
