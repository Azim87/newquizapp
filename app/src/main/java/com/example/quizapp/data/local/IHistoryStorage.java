package com.example.quizapp.data.local;

import androidx.lifecycle.LiveData;

import com.example.quizapp.models.History;
import com.example.quizapp.models.QuizResult;

import java.util.List;

public interface IHistoryStorage {
    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    int deleteAll();

    int deleteById(int id);

    LiveData<List<History>> getAllHistory();

    LiveData<List<QuizResult>> getAll();
}
