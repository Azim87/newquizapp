package com.example.quizapp.ui.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.ui.quiz.adapter.QuizAdapter;
import com.example.quizapp.utils.ShowToast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity {
    private QuizViewModel quizViewModel;
    private int amount;
    private int category;
    private String difficulty;
    private Integer position = 0;
    private QuizAdapter adapter;

    @BindView(R.id.quiz_recycler)
    RecyclerView quizRecycler;
    @BindView(R.id.quiz_progress)
    ProgressBar quizProgress;
    @BindView(R.id.quiz_category)
    TextView quizTextView;
    @BindView(R.id.quiz_skip_button)
    Button quizSkipButton;

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
        ButterKnife.bind(this);
        quizViewModel = ViewModelProviders.of(this)
                .get(QuizViewModel.class);
        adapter = new QuizAdapter();
        getExtraIntentData();
        subscribeToViewModel();
        initViews();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        quizRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        quizRecycler.setAdapter(adapter);
        quizRecycler.setOnTouchListener((v, event) -> true);
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
        quizViewModel.currentQuestionPosition.observe(this, integer -> {
            Log.d("ololo", "subscribeToViewModel: " + integer);
            position = integer;
            ShowToast.message(" " + integer);
        });

        quizViewModel.questionList.observe(this, questions -> {
            //
            Log.d("ololo", "onChanged: " + questions.get(position).getCategory());
            quizTextView.setText(questions.get(position).getCategory());
        });
    }
}
