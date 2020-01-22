package com.example.quizapp.utils;

import android.util.Log;

public class Logger {
    private static final String TAG = "------------------------------";

    public static void showLog(String message) {
        Log.d(TAG, message);
    }
}
