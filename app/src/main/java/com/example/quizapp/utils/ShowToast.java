package com.example.quizapp.utils;

import android.widget.Toast;

import com.example.quizapp.App;

public class ShowToast {
    public static void message(String message) {
        Toast.makeText(App.instance, message, Toast.LENGTH_SHORT).show();
    }
}
