package com.example.quizapp.ui.settings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.R;
import com.example.quizapp.base.BaseFragment;

public class SettingsFragment extends BaseFragment {

    private SettingViewModel settingViewModel;

    public static Fragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void setUpView(View view) {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settingViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        subscribeToViewModel();
    }

    private void subscribeToViewModel() {

    }
}
