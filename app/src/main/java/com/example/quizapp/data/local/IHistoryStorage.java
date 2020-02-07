package com.example.quizapp.data.local;

import androidx.lifecycle.LiveData;

import com.example.quizapp.models.QuizResult;

import java.util.List;

public interface IHistoryStorage {
    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    int deleteAll();

    int deleteById(int id);

    int getById(int id);

    LiveData<List<QuizResult>> getAll();
}
