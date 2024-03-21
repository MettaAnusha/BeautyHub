package com.example.beautyhub.servercommunication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerCommunicationOTP {

    // Define the URL to your OTP validation endpoint
    private static final String OTP_VALIDATION_URL = "http://192.168.150.1/beautyhub/validate.php";

    public interface OTPValidationListener {
        void onOTPValidationComplete(String response);
    }

    public static void validateOTP(String otp, OTPValidationListener listener) {
        new OTPValidationTask(listener).execute(otp);
    }

    private static class OTPValidationTask extends AsyncTask<String, Void, String> {
        private OTPValidationListener listener;

        OTPValidationTask(OTPValidationListener listener) {
            this.listener = listener;
        }

        @Override
        protected String doInBackground(String... params) {
            String response = "";

            try {
                // Define the URL to your OTP validation endpoint
                String otp = params[0];
                String urlString = OTP_VALIDATION_URL + "?otp=" + otp;
                URL url = new URL(urlString);

                // Open a connection
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // Get the response from the server
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer responseData = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    responseData.append(inputLine);
                }
                in.close();

                // Set the response to return
                response = responseData.toString();

                // Close the connection
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }


        @Override
        protected void onPostExecute(String response) {
            if (listener != null) {
                // Print the response to the logcat
                Log.d("ServerCommunicationOTP", "Server Response: " + response);

                // Notify the listener with the response
                listener.onOTPValidationComplete(response);
            }
        }

    }
}
