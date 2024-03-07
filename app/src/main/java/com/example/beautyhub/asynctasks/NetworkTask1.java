package com.example.beautyhub.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.beautyhub.servercommunication.ServerCommunication1;

public class NetworkTask1 extends AsyncTask<String, Void, String> {
    private TaskCompletionListener listener;

    public NetworkTask1(TaskCompletionListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String serviceName = params[0];
        Log.d("NetworkTask1", "Networktask1: " + serviceName);

        // Call your method to send data to the server
        return ServerCommunication1.sendDataToServer(serviceName);
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
