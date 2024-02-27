package com.example.beautyhub;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerCommunicationLogin {
    public static String sendDataToServer(String email, String password, String phone) {
        String response = "";

        try {
            // Define the URL to your PHP script
            URL url = new URL("http://192.168.150.1/beautyhub/customersvalidation.php");

            // Open a connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Create the data to send
            String data =  "&custemail=" + email +"&custpassword=" + password + "&phonenumber=" + phone;


            // Write data to the connection output stream
            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes("UTF-8"));
            os.flush();
            os.close();

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
}
