package com.example.quizapp.ui.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.R;
import com.example.quizapp.utils.ShowToast;

public class QuizActivity extends AppCompatActivity {
    private QuizViewModel quizViewModel;
    private int amount;
    private int category;
    private String difficulty;

    public static void start(Context context, int amount, int category, String difficulty) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra("amount", amount);
        intent.putExtra("category", category);
        intent.putExtra("difficulty", difficulty);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        quizViewModel = ViewModelProviders.of(this)
                .get(QuizViewModel.class);
        getExtraIntentData();
        subscribeToViewModel();
    }

    private void getExtraIntentData() {
        amount = getIntent().getIntExtra("amount", 0);
        category = getIntent().getIntExtra("category", 0);
        difficulty = getIntent().getStringExtra("difficulty").toLowerCase();
        if (difficulty.equals("any difficulty")) {
            difficulty = null;
        }
        quizViewModel.getQuizQuestion(amount, category, difficulty);
    }

    private void subscribeToViewModel() {
        quizViewModel.currentQuestionPosition.observe(this, integer ->
                ShowToast.message(" " + integer));

    }
}
