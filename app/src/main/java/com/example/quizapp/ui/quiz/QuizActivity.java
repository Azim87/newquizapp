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
    private final static String EXTRA_AMOUNT = "amount";
    private final static String EXTRA_CATEGORY = "category";
    private final static String EXTRA_DIFFICULTY = "difficulty";
    private QuizViewModel quizViewModel;
    private int amount;
    private int category;
    private String difficulty;
    private QuizAdapter adapter;

    @BindView(R.id.quiz_recycler) RecyclerView quizRecycler;
    @BindView(R.id.quiz_progress) ProgressBar quizProgress;
    @BindView(R.id.progress_bar) ProgressBar loadingProgessBar;
    @BindView(R.id.quiz_category) TextView quizCategory;
    @BindView(R.id.progress_count) TextView quizProgressTextView;
    @BindView(R.id.quiz_skip_button) Button quizSkipButton;

    public static void start(Context context, int amount, int category, String difficulty) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        subscribeToViewModel();
        getExtraIntentData();
        initViews();
        loadingProgessBar.setVisibility(View.VISIBLE);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        adapter = new QuizAdapter();
        quizRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        quizRecycler.setAdapter(adapter);
        quizRecycler.setOnTouchListener((v, event) -> true);
    }

    private void getExtraIntentData() {
        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 0);
        category = getIntent().getIntExtra(EXTRA_CATEGORY, 0);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY).toLowerCase();
        if (difficulty.equals("any difficulty")) {
            difficulty = null;
        }
        quizViewModel.getQuizQuestion(amount, category, difficulty);
    }

    private void subscribeToViewModel() {
        quizViewModel = ViewModelProviders.of(this)
                .get(QuizViewModel.class);
        quizViewModel.questionList.observe(this, questions -> {
            loadingProgessBar.setVisibility(View.INVISIBLE);
            quizProgress.setMax(questions.size());
            adapter.setList(questions);
        });

        quizViewModel.currentQuestionPosition.observe(this, position -> {
            quizCategory.setText(adapter.getListPosition().get(position).getCategory());
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
