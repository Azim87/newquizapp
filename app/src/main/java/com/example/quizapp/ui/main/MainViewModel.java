package com.example.quizapp.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<Integer> count = new MutableLiveData<>();

    public void getCount() {
        count.setValue(0);
    }

    void countIncrement() {
        Integer currentCount = count.getValue();
        if (currentCount != null)
            count.setValue(currentCount + 1);
    }

    void countDecrement() {
        Integer currentCount = count.getValue();
        if (currentCount != null)
            count.setValue(currentCount - 1);
    }
}
