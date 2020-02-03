package com.example.quizapp.ui.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.data.IQuizRepository;
import com.example.quizapp.models.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizViewModel extends ViewModel {
    private IQuizRepository quizRepository = App.iQuizRepository;
    public MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    public MutableLiveData<List<Question>> questionList = new MutableLiveData<>();

    public void getQuizQuestion(int amount, int category, String difficulty) {
        quizRepository.getQuizQuestions(amount, category, difficulty, new IQuizRepository.QuizCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                if (result != null) {
                    questionList.setValue(result);
                    currentQuestionPosition.setValue(0);

                    for (int i =0; i < result.size(); i++) {
                        Question question = result.get(i);
                        result.set(i, shuffleQuestions(question));
                    }
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("ololo", "on failure: " + e.getLocalizedMessage());
            }
        });
    }

    public void onSkipClick() {
        currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
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
