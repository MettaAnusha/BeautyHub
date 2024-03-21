//package com.example.beautyhub.servercommunication;
//
//import android.util.Log;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class ServerCommunicationForgotPassword {
//    public static boolean sendDataToServer(String phonenumber) {
//        boolean isValidPhoneNumber = false;
//
//        try {
//            // Define the URL to your PHP script
//            URL url = new URL("http://192.168.150.1/beautyhub/otp.php");
//
//            // Open a connection
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setDoOutput(true);
//
//            // Create the data to send
//            String data = "phonenumber=" + phonenumber;
//            Log.d("servercommunication", "**************" + phonenumber);
//
//            // Write data to the connection output stream
//            OutputStream os = conn.getOutputStream();
//            os.write(data.getBytes("UTF-8"));
//            os.flush();
//            os.close();
//
//            // Get the response from the server
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuilder response = new StringBuilder(); // String to store the response
//            String inputLine;
//
//            // Read the response and append it to the StringBuilder
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            // Log the response
//            Log.d("servercommunication", "Response from server: " + response.toString());
//
//            // Assuming the response is a boolean value in string format
//            isValidPhoneNumber = Boolean.parseBoolean(response.toString().trim());
//
//            // Close the connection
//            conn.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return isValidPhoneNumber;
//    }
//}


package com.example.beautyhub.servercommunication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerCommunicationForgotPassword {
    public static String sendDataToServer(String phonenumber) {
        String response = "";

        try {
            // Define the URL to your PHP script
            URL url = new URL("http://192.168.150.1/beautyhub/otp.php?phonenumber=" + phonenumber);

            // Open a connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Get the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responseBuilder.append(inputLine);
            }
            in.close();

            // Set the response
            response = responseBuilder.toString();

            // Close the connection
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
