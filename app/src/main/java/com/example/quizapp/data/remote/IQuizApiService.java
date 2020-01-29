package com.example.quizapp.data.remote;

import com.example.quizapp.base.BaseCallBack;
import com.example.quizapp.models.Question;

public interface IQuizApiService {

    void getQuestions(int amount, Integer category, String difficulty, QuestionCallBack questionCallBack);

    interface QuestionCallBack extends BaseCallBack<Question> {
    }
}
