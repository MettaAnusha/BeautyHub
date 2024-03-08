package com.example.beautyhub.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.beautyhub.servercommunication.ServerCommunication1;

public class NetworkTask4 extends AsyncTask<String, Void, String> {
    private TaskCompletionListener listener;

    public NetworkTask4(TaskCompletionListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String proname = params[0];
        Log.d("NetworkTask4", "Networktask4: " + proname);

        // Call your method to send data to the server
        return ServerCommunication1.sendDataToServer(proname);
    }

    @Override
    protected void onPostExecute(String result) {
        // Process the result here
        Log.d("NetworkTask4", "Response*********: " + result);
        if (listener != null) {
            listener.onTaskCompleted(result);
        }
    }

    // Interface to communicate result back to the activity
    public interface TaskCompletionListener {
        void onTaskCompleted(String result);
    }
}
