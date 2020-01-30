package com.example.quizapp.ui.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.ui.quiz.adapter.QuizAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    TextView quizCategory;
    @BindView(R.id.progress_count)
    TextView quizProgressTextView;
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
        subscribeToViewModel();
        getExtraIntentData();
        initViews();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        adapter = new QuizAdapter();
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
        quizViewModel.questionList.observe(this, questions -> {
            quizProgress.setMax(questions.size());
            adapter.setList(questions);
        });

        quizViewModel.currentQuestionPosition.observe(this, position -> {
            quizProgress.setProgress(position + 1);
            quizProgressTextView.setText(position + 1 + "/" + adapter.getItemCount());
            quizRecycler.smoothScrollToPosition(position);
        });
    }

    @OnClick(R.id.quiz_skip_button)
    void onSkipClick(View view) {
        quizViewModel.onSkipClick();
    }
}