package com.example.quizapp.data;

import com.example.quizapp.base.IBaseCallBack;
import com.example.quizapp.models.Question;

import java.util.List;

public interface IQuizRepository {
    void getQuizQuestions(int amount, Integer category, String difficulty, QuizCallBack callBack);

    interface QuizCallBack extends IBaseCallBack<List<Question>> {}
}
