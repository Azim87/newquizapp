package com.example.quizapp.data.local;

import com.example.quizapp.models.QuizResult;

public interface IHistoryStorage {

    public QuizResult getQuizResult(int id);
    public int saveQuizResult(QuizResult quizResult);
    public int deleteAll();
    public int deleteById(int id);
}
