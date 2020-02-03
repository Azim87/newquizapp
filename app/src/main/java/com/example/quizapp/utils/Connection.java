package com.example.quizapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Connection {
    public static boolean isInternetAvailable(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return false;
    }
}
