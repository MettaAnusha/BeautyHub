package com.example.beautyhub.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.beautyhub.servercommunication.ServerCommunication2;
import com.example.beautyhub.servercommunication.ServerCommunicationPro;

public class NetworkTask2 extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        String date = params[0];
        String time = params[1];
        String proname = params[2];
        String subservicename = params[3];
        String subserviceprice = params[4];

        // Call your method to send data to the server
        return ServerCommunication2.sendDataToServer(date, time, proname, subservicename,subserviceprice);
    }

    @Override
    protected void onPostExecute(String result) {
        // Process the result here
        Log.d("NetworkTask", "Response: " + result);
    }
}
