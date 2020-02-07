package com.example.quizapp.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.data.IQuizRepository;
import com.example.quizapp.models.QuizResult;

import java.util.List;

public class HistoryViewModel extends ViewModel {
    private IQuizRepository repository = App.iQuizRepository;

    LiveData<List<QuizResult>> quizResult = repository.getAll();

}
