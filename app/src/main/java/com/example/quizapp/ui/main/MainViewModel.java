package com.example.quizapp.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<Integer> count = new MutableLiveData<>();

    int counter = 0;

    void countIncrement() {
        count.setValue(++counter);
    }

    void countDecrement() {
        count.setValue(--counter);
    }

}
