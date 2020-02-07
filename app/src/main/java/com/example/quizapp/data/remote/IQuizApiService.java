package com.example.quizapp.data.remote;

import com.example.quizapp.data.IQuizRepository;

public interface IQuizApiService {
    void getQuestions(
            int amount,
            Integer category,
            String difficulty,
            String type,
            IQuizRepository.QuizCallBack callBack
    );
}
