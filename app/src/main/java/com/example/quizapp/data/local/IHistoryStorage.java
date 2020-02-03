package com.example.quizapp.data.local;

import androidx.lifecycle.LiveData;

import com.example.quizapp.models.QuizResult;

import java.util.List;

public interface IHistoryStorage {

    public QuizResult getQuizResult(int id);
    public int saveQuizResult(QuizResult quizResult);
    public int deleteAll();
    public int deleteById(int id);
    public LiveData<List<QuizResult>> getAll();
}
