package com.example.beautyhub.asynctasks;

import android.os.AsyncTask;
import android.util.Log;


import com.example.beautyhub.servercommunication.ServerCommunicationAddPassword;

public class NetworkTaskAddPassword extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String custpassword = params[0];
        String phonenumber=params[1];
        Log.d("custpassword","custpassword"+custpassword);
        Log.d("phonenumber","phonenumber"+phonenumber);



        // Call your method to send data to the server
        return ServerCommunicationAddPassword.sendDataToServer(custpassword,phonenumber);
    }

    @Override
    protected void onPostExecute(String result) {
        // Process the result here
        Log.d("NetworkTask", "Response: " + result);
    }

}
