package com.example.quizapp.ui.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.data.IQuizRepository;
import com.example.quizapp.data.remote.IQuizApiService;
import com.example.quizapp.models.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {
    private IQuizRepository quizRepository = App.iQuizRepository;
    public MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();

    public void getQuizQuestion(int amount, int category, String difficulty) {
        quizRepository.getQuizQuestions(amount, category, difficulty, new IQuizApiService.QuestionCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                if (result != null) {
                    currentQuestionPosition.setValue(0);
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
