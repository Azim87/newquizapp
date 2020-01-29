package com.example.quizapp.ui.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.R;
import com.example.quizapp.base.BaseFragment;
import com.example.quizapp.ui.main.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsFragment extends BaseFragment {
    @BindView(R.id.count_view)
    TextView textView;

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
    }
}
