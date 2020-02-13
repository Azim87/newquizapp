package com.example.quizapp.data;

import androidx.lifecycle.LiveData;

import com.example.quizapp.base.IBaseCallBack;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.QuizResult;

import java.util.List;

public interface IQuizRepository {
    void getQuizQuestions(int amount, Integer category, String difficulty, String type, QuizCallBack callBack);

    interface QuizCallBack extends IBaseCallBack<List<Question>> {}
}
