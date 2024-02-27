package com.example.beautyhub;

import android.os.AsyncTask;
import android.util.Log;

public class NetworkTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String custName = params[0];
        String custEmail = params[1];
        String custPassword = params[2];
        String phoneNumber = params[3];

        // Call your method to send data to the server
        return ServerCommunication.sendDataToServer(custName, custEmail, custPassword, phoneNumber);
    }

    @Override
    protected void onPostExecute(String result) {
        // Process the result here
        Log.d("NetworkTask", "Response: " + result);
    }
}

