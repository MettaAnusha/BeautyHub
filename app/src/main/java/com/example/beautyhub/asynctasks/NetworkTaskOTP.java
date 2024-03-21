package com.example.beautyhub.asynctasks;

import android.os.AsyncTask;

import com.example.beautyhub.servercommunication.ServerCommunicationOTP;

public class NetworkTaskOTP extends AsyncTask<String, Void, String> implements ServerCommunicationOTP.OTPValidationListener {

    private OnTaskCompleted listener;

    public interface OnTaskCompleted {
        void onTaskCompleted(String response);
    }

    public NetworkTaskOTP(OnTaskCompleted listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String otp = params[0];

        // Call your method to send OTP to the server for validation
        ServerCommunicationOTP.validateOTP(otp, this);
        // Return null here, as the actual response will be delivered via the interface method
        return null;
    }

    @Override
    public void onOTPValidationComplete(String response) {
        // Pass the response back to the activity using the callback interface
        if (listener != null) {
            listener.onTaskCompleted(response);
        }
    }
}
