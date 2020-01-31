package com.example.quizapp.data;

import com.example.quizapp.data.remote.IQuizApiService;
import com.example.quizapp.models.Question;

import java.util.List;

public class QuizRepository implements IQuizRepository {
    private IQuizApiService mQuizApiClient;

    public QuizRepository(IQuizApiService apiService) {
        mQuizApiClient = apiService;
    }

    @Override
    public void getQuizQuestions(int amount, Integer category, String difficulty, QuizCallBack callBack) {
        mQuizApiClient.getQuestions(amount, category, difficulty, new QuizCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.onFailure(new Exception(e.getMessage()));
            }
        });

    }
}
