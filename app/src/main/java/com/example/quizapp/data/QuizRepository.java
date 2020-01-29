package com.example.quizapp.data;

import com.example.quizapp.data.remote.IQuizApiService;
import com.example.quizapp.data.remote.QuizApiClient;
import com.example.quizapp.models.Question;

import java.util.List;

public class QuizRepository implements IQuizRepository {
    private QuizApiClient mQuizApiClient;

    public QuizRepository(QuizApiClient quizApiClient) {
        mQuizApiClient = quizApiClient;
    }

    @Override
    public void getQuizQuestions(int amount, Integer category, String difficulty,
                                 IQuizApiService.QuestionCallBack questionCallBack) {

        mQuizApiClient.getQuestions(amount, category, difficulty, new IQuizApiService.QuestionCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                questionCallBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                questionCallBack.onFailure(new Exception(e.getMessage()));
            }
        });
    }
}
