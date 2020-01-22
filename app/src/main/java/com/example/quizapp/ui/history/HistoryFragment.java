package com.example.quizapp.ui.history;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.R;
import com.example.quizapp.base.BaseFragment;
import com.example.quizapp.ui.main.MainViewModel;

public class HistoryFragment extends BaseFragment {
    private MainViewModel historyViewModel;


    public static Fragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void setUpView(View view) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        historyViewModel = ViewModelProviders.of(getActivity())
                .get(MainViewModel.class);
    }
}
