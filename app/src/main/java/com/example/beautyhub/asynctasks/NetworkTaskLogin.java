package com.example.beautyhub.asynctasks;

import android.os.AsyncTask;

import com.example.beautyhub.servercommunication.ServerCommunicationLogin;

public class NetworkTaskLogin extends AsyncTask<String, Void, String> {
    protected String doInBackground(String... params) {
        String email = params[0];
        String password = params[1];
        String phone = params[2];


        // Call your method to send data to the server
        return ServerCommunicationLogin.sendDataToServer(email, password, phone);
    }

    @Override
    protected void onPostExecute(String result) {
        // Pass the result to the interface method
        listener.onTaskCompleted(result);
    }
    private OnTaskCompleted listener; // Interface reference

    // Interface to handle result
    public interface OnTaskCompleted {
        void onTaskCompleted(String result);
    }

    // Constructor to initialize listener
    public NetworkTaskLogin(OnTaskCompleted listener) {
        this.listener = listener;
    }

}
