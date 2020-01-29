package com.example.quizapp.ui.main;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.data.IQuizRepository;
import com.example.quizapp.data.remote.IQuizApiService;
import com.example.quizapp.models.Question;

import java.util.List;

public class MainViewModel extends ViewModel {
    private IQuizRepository mQuizRepository = App.iQuizRepository;

    public MainViewModel() {
    }

    public void getQuizQuestions(int amount, Integer category, String hard) {
        mQuizRepository.getQuizQuestions(amount, category, hard,
                new IQuizApiService.QuestionCallBack() {
                    @Override
                    public void onSuccess(List<Question> result) {
                        Log.d("ololo", "on success: " + result.get(0).getQuestion());
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d("ololo", "on failure: " + e.getLocalizedMessage());
                    }
                });
    }
}
