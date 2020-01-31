package com.example.quizapp.ui.main;

import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.base.BaseFragment;
import com.example.quizapp.ui.quiz.QuizActivity;
import com.example.quizapp.utils.SimpleSeekBarChangeListener;
import com.example.quizapp.utils.SpinnerHelper;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {
    @BindView(R.id.amount_seekbar) SeekBar seekBar;
    @BindView(R.id.category_spinner) NiceSpinner categorySpinner;
    @BindView(R.id.difficulty_spinner) NiceSpinner difficultySpinner;
    @BindView(R.id.question_amount_quantity) TextView questionAmount;
    private Animation animation;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void setUpView(View view) {
        getSeekBarProgress();
        initCategorySpinner();
        initDifficultySpinner();
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.button_anim);
    }

    private void getSeekBarProgress() {
        questionAmount.setText(String.valueOf(seekBar.getProgress()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(5);
        }
        seekBar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                questionAmount.setText(String.valueOf(progress));
            }
        });
    }

    //region category spinner list
    private void initCategorySpinner() {
        List<String> category = new LinkedList<>(Arrays.asList("ANY CATEGORY"));
        category.add("GENERAL KNOWLEDGE");
        category.add("ENTERTAINMENT: BOOKS");
        category.add("ENTERTAINMENT: FILM");
        category.add("ENTERTAINMENT: MUSIC");
        category.add("ENTERTAINMENT: MUSICALS & THEATRES");
        category.add("ENTERTAINMENT: TELEVISION");
        category.add("ENTERTAINMENT: VIDEO GAMES");
        category.add("ENTERTAINMENT: BOARD GAMES");
        category.add("SCIENCE & NATURE");
        category.add("SCIENCE: COMPUTERS");
        category.add("SCIENCE: MATHEMATICS");
        category.add("MYTHOLOGY");
        category.add("SPORTS");
        category.add("GEOGRAPHY");
        category.add("HISTORY");
        category.add("POLITICS");
        category.add("ART");
        category.add("CELEBRITIES");
        category.add("ANIMALS");
        category.add("VEHICLES");
        category.add("ENTERTAINMENT: COMICS");
        category.add("SCIENCE: GADGETS");
        category.add("ENTERTAINMENT: JAPANESE ANIME & MANGA");
        category.add("ENTERTAINMENT: CARTOON & ANIMATIONS");
        SpinnerHelper.set(category, categorySpinner);
    }
    //endregion

    //region difficulty spinner list
    private void initDifficultySpinner() {
        List<String> difficulty = new LinkedList<>(Arrays.asList("ANY DIFFICULTY"));
        difficulty.add("EASY");
        difficulty.add("MEDIUM");
        difficulty.add("HARD");
        SpinnerHelper.set(difficulty, difficultySpinner);
    }
    //endregion

    @OnClick(R.id.button_start)
    public void startOnClick(View view) {
        view.startAnimation(animation);
        new Handler().postDelayed(() -> {
            int categoryId = 0;
            if (categorySpinner.getSelectedIndex() != 0) {
                categoryId = categorySpinner.getSelectedIndex() + 8;
            }
            QuizActivity.start(getContext(),
                    seekBar.getProgress(),
                    categoryId,
                    difficultySpinner.getSelectedItem().toString());
        },500);

    }
}
