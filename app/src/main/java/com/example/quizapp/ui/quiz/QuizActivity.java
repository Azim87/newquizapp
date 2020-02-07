package com.example.quizapp.ui.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.ui.quiz.adapter.QuizAdapter;
import com.example.quizapp.ui.result.ResultActivity;
import com.example.quizapp.utils.ShowToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.quizapp.R.anim.button_anim;

public class QuizActivity extends AppCompatActivity implements QuizAdapter.OnQuestionClickListener {
    private final static String EXTRA_AMOUNT = "amount";
    private final static String EXTRA_CATEGORY = "category";
    private final static String EXTRA_DIFFICULTY = "difficulty";
    private final static String EXTRA_TYPE = "type";
    private QuizViewModel quizViewModel;
    private QuizAdapter adapter;
    private CountDownTimer countDownTimer;
    private long startTimer = 15000;

    @BindView(R.id.quiz_recycler) RecyclerView quizRecycler;
    @BindView(R.id.quiz_progress) ProgressBar quizProgress;
    @BindView(R.id.progress_bar) ProgressBar loadingProgressBar;
    @BindView(R.id.quiz_category) TextView quizCategory;
    @BindView(R.id.progress_count) TextView quizProgressTextView;
    @BindView(R.id.quiz_skip_button) Button quizSkipButton;
    @BindView(R.id.progress_timer) TextView progressTimer;

    public static void start(Context context, int amount, int category, String difficulty, String type) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(EXTRA_TYPE, type);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        initViews();
        subscribeToViewModel();
        getExtraIntentData();
        loadingProgressBar.setVisibility(View.VISIBLE);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        adapter = new QuizAdapter(this);
        quizRecycler.setLayoutManager(new LinearLayoutManager(
                this,
                RecyclerView.HORIZONTAL,
                false
        ));
        quizRecycler.setAdapter(adapter);
        quizRecycler.setOnTouchListener((v, event) -> true);
    }

    @SuppressLint("SetTextI18n")
    private void subscribeToViewModel() {
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        quizViewModel.questionList.observe(this, questions -> {
            loadingProgressBar.setVisibility(View.INVISIBLE);
            quizSkipButton.setVisibility(View.VISIBLE);
            quizProgress.setMax(questions.size());
            adapter.setList(questions);
        });

        quizViewModel.currentQuestionPosition.observe(this, position -> {
            quizCategory.setText(adapter.getListPosition().get(position).getCategory());
            quizProgress.setProgress(position + 1);
            quizProgressTextView.setText(position + 1 + "/" + adapter.getItemCount());
            quizRecycler.smoothScrollToPosition(position);
            countDownTimer.cancel();
            progressTimer.setTextColor(Color.BLUE);
            progressTimer.setVisibility(View.VISIBLE);
            countDownTimer.start();
        });

        quizViewModel.finishEvent.observe(this, aVoid -> {
            ResultActivity.start(QuizActivity.this);
            finish();
        });

        quizViewModel.message.observe(this, message -> {
            ShowToast.message(message);
        });

        countDownTimer = new CountDownTimer(startTimer, 1) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                progressTimer.setText(String.format(" %d", millisUntilFinished / 1000));
                if (millisUntilFinished <= 6000) {
                    progressTimer.setTextColor(Color.RED);

                    if (progressTimer.getVisibility() == View.VISIBLE) {
                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressTimer.setVisibility(View.INVISIBLE);
                    } else {
                        progressTimer.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFinish() {
                quizViewModel.onSkipClick();
            }
        };
    }

    private void getExtraIntentData() {
        int amount = getIntent().getIntExtra(EXTRA_AMOUNT, 0);
        int category = getIntent().getIntExtra(EXTRA_CATEGORY, 0);
        String difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY).toLowerCase();
        String type = getIntent().getStringExtra(EXTRA_TYPE).toLowerCase();
        if (difficulty.equals("any difficulty")) {
            difficulty = null;
        }
        if (type.equals("any type")) {
            type = null;
        }
        quizViewModel.getQuizQuestion(amount, category, difficulty, type);
    }

    @Override
    public void onBackPressed() {
        quizViewModel.onBackPressed();
    }

    @OnClick(R.id.quiz_skip_button)
    void onSkipClick(View view) {
        final Animation animation = AnimationUtils
                .loadAnimation(this, button_anim);
        view.startAnimation(animation);
        quizViewModel.onSkipClick();
    }

    @Override
    public void onAnswerClick(int questionPosition, int answerPosition) {
        quizViewModel.onAnswerPositionClick(questionPosition, answerPosition);
    }

    @OnClick(R.id.image_back)
    void onBackPressed(View view) {
        quizViewModel.onBackPressed();
    }
}
