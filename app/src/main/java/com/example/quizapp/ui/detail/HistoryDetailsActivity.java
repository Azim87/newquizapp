package com.example.quizapp.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HistoryDetailsActivity extends AppCompatActivity {


    public static void start(Context context) {
        context.startActivity(new Intent(context, HistoryDetailsActivity.class));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
