package com.example.quizapp;

import android.app.Application;

import com.example.quizapp.data.QuizRepository;
import com.example.quizapp.data.IQuizRepository;
import com.example.quizapp.data.local.HistoryStorage;
import com.example.quizapp.data.remote.QuizApiClient;

public class App extends Application {
    public static App instance;
    public static IQuizRepository iQuizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        iQuizRepository = new QuizRepository(
                new QuizApiClient(),
                new HistoryStorage()
        );
    }
}
