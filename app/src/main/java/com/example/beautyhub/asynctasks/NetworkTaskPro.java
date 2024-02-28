package com.example.beautyhub.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.beautyhub.servercommunication.ServerCommunicationPro;

public class NetworkTaskPro extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String proName = params[0];
        String proEmail = params[1];
        String proPassword = params[2];
        String phoneNumber = params[3];
        String qualification = params[4];

        // Call your method to send data to the server
        return ServerCommunicationPro.sendDataToServer(proName, proEmail, proPassword, phoneNumber, qualification);
    }

    @Override
    protected void onPostExecute(String result) {
        // Process the result here
        Log.d("NetworkTask", "Response: " + result);
    }
}
