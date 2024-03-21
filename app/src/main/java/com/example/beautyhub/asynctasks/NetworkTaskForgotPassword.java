package com.example.beautyhub.asynctasks;

import android.os.AsyncTask;

import com.example.beautyhub.servercommunication.ServerCommunicationForgotPassword;

public class NetworkTaskForgotPassword extends AsyncTask<String, Void, String> {
    // Define an interface for communication between AsyncTask and activity
    public interface AsyncResponse {
        void processFinish(String response);
    }

    // Create an instance of the interface
    public AsyncResponse delegate = null;

    // Constructor to set the delegate
    public NetworkTaskForgotPassword(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        String phonenumber = params[0];

        // Call your method to send data to the server
        return ServerCommunicationForgotPassword.sendDataToServer(phonenumber);
    }

    @Override
    protected void onPostExecute(String response) {
        // Pass the response back to the activity
        delegate.processFinish(response);
    }
}
