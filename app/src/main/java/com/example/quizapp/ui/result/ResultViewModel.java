package com.example.quizapp.ui.result;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.models.QuizResult;

public class ResultViewModel extends ViewModel {
    MutableLiveData<QuizResult> resultMutableLiveData = new MutableLiveData<>();

    public void getResult(int id) {
        QuizResult result = App.quizDataBase.getHistoryDao().get(id);
        Log.d("ololo", "getResult: " + result);
        resultMutableLiveData.setValue(result);
    }
}
