package com.example.quizapp.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.R;
import com.example.quizapp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {
    @BindView(R.id.counter_text_view)
    TextView counterTextView;
    @BindView(R.id.button_increment)
    Button incrementBtn;
    @BindView(R.id.button_decrement)
    Button decrementBtn;

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
        ButterKnife.bind(this, view);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders
                .of(getActivity()).get(MainViewModel.class);
        mainViewModel.getCount();
        mainViewModel.count.observe(this, integer ->
                counterTextView.setText(String.valueOf(integer)));
    }

    @OnClick(R.id.button_decrement)
    public void decrement(View view) {
        mainViewModel.countDecrement();
    }

    @OnClick(R.id.button_increment)
    public void increment(View view) {
        mainViewModel.countIncrement();
    }
}
