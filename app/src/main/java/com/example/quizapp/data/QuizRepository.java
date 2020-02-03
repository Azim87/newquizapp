package com.example.quizapp.data;

import com.example.quizapp.data.remote.IQuizApiService;
import com.example.quizapp.models.Question;

import java.util.ArrayList;
import java.util.Collections;
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
                if (result != null) {
                    callBack.onSuccess(result);

                    for (int i = 0; i < result.size(); i++) {
                        Question question = result.get(i);
                        result.set(i, shuffleQuestions(question));
                    }
                }

            }

            @Override
            public void onFailure(Exception e) {
                callBack.onFailure(new Exception(e.getMessage()));
            }
        });
    }

    private Question shuffleQuestions(Question question) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswers());
        Collections.shuffle(answers);
        question.setAnswers(answers);


        return question;
    }
}
