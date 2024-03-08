package com.example.beautyhub.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.beautyhub.servercommunication.ServerCommunication3;

public class NetworkTask3 extends AsyncTask<String, Void, String> {
    private TaskCompletionListener listener;

    public NetworkTask3(TaskCompletionListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String servicename = params[0];
        Log.d("NetworkTask1", "Networktask1: " + servicename);

        // Call your method to send data to the server
        return ServerCommunication3.sendDataToServer(servicename);
    }

    @Override
    protected void onPostExecute(String result) {
        // Process the result here
        Log.d("NetworkTask1", "Response*********: " + result);
        if (listener != null) {
            listener.onTaskCompleted(result);
        }
    }

    // Interface to communicate result back to the activity
    public interface TaskCompletionListener {
        void onTaskCompleted(String result);
    }
}
