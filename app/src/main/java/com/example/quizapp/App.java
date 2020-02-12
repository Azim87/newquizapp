package com.example.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.example.quizapp.data.IQuizRepository;
import com.example.quizapp.data.QuizRepository;
import com.example.quizapp.data.local.HistoryStorage;
import com.example.quizapp.data.local.db.QuizDataBase;
import com.example.quizapp.data.remote.QuizApiClient;

public class App extends Application {
    public static App instance;
    public static IQuizRepository iQuizRepository;
    public static QuizDataBase quizDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        quizDataBase = Room.databaseBuilder(
                instance,
                QuizDataBase.class,
                "quiz_app"
        ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        iQuizRepository = new QuizRepository(
                new QuizApiClient(),
                new HistoryStorage(quizDataBase.getHistoryDao())
        );
    }
}
