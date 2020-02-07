package com.example.quizapp.data;

import androidx.lifecycle.LiveData;

import com.example.quizapp.base.IBaseCallBack;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.QuizResult;

import java.util.List;

public interface IQuizRepository {
    void getQuizQuestions(int amount, Integer category, String difficulty, String type, QuizCallBack callBack);

    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    void deleteAll();

    int deleteById(int id);

    int getById(int id);

    LiveData<List<QuizResult>> getAll();

    interface QuizCallBack extends IBaseCallBack<List<Question>> {}
}
