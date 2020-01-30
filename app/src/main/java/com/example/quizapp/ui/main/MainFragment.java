package com.example.quizapp.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.R;
import com.example.quizapp.base.BaseFragment;
import com.example.quizapp.utils.SimpleSeekBarChangeListener;

import org.angmarch.views.NiceSpinner;

import butterknife.BindView;

public class MainFragment extends BaseFragment {
    @BindView(R.id.amount_seekbar)
    SeekBar seekBar;
    @BindView(R.id.category_spinner)
    NiceSpinner categorySpinner;
    @BindView(R.id.difficulty_spinner)
    NiceSpinner difficultySpinner;
    @BindView(R.id.question_amount_quantity)
    TextView questionAmount;

    private MainViewModel mainViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void setUpView(View view) {
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        subscribeToViewModel();
    }

    private void subscribeToViewModel() {
        mainViewModel.getQuizQuestions(10, 9, "easy");
    }
}
