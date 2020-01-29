package com.example.quizapp;

import android.app.Application;

import com.example.quizapp.data.QuizRepository;
import com.example.quizapp.data.remote.IQuizRepository;

public class App extends Application {
    public static App instance;
    public IQuizRepository iQuizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        iQuizRepository = new QuizRepository();
    }
}
