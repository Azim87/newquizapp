package com.example.quizapp.splash;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.R;
import com.example.quizapp.ui.main.MainActivity;
import com.example.quizapp.utils.Logger;

public class SplashActivity extends AppCompatActivity {
    private static long splashTime = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new CountDownTimer(splashTime, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                splashTime = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                getMainActivity();
            }
        }.start();
    }

    private void getMainActivity() {
        MainActivity.start(this);
        finish();
    }
}
