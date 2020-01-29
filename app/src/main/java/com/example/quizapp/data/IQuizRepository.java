package com.example.quizapp.data;

import com.example.quizapp.data.remote.IQuizApiService;

public interface IQuizRepository {
    void getQuizQuestions(int amount, Integer category, String difficulty, IQuizApiService.QuestionCallBack questionCallBack);
}
