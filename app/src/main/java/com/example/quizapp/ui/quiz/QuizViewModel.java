package com.example.quizapp.ui.quiz;

import android.util.Log;

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
    public MutableLiveData<List<Question>> questionList = new MutableLiveData<>();

    public void getQuizQuestion(int amount, int category, String difficulty) {
        quizRepository.getQuizQuestions(amount, category, difficulty, new IQuizApiService.QuestionCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                if (result != null) {
                    currentQuestionPosition.setValue(0);
                    questionList.setValue(result);
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("ololo", "on failure: " + e.getLocalizedMessage());
            }
        });
    }
}
