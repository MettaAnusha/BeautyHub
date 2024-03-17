package com.example.beautyhub.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.beautyhub.servercommunication.ServerCommunicationCancel;

public class NetworkTaskCancel extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String proname = params[0];
        String subservicename = params[1];
        String subserviceprice=params[2];
        String date = params[3];
        String time = params[4];




        // Call your method to send data to the server
        return ServerCommunicationCancel.sendDataToServer(proname, subservicename, subserviceprice, date, time);
    }

    @Override
    protected void onPostExecute(String result) {
        // Process the result here
        Log.d("NetworkTask", "Response: " + result);
    }

}
