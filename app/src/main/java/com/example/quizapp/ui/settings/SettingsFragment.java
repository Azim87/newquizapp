package com.example.quizapp.ui.settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.airbnb.lottie.LottieAnimationView;
import com.example.quizapp.R;
import com.example.quizapp.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsFragment extends BaseFragment {
    @BindView(R.id.share) TextView shareTV;
    @BindView(R.id.clear) TextView clearTV;
    @BindView(R.id.loading) LottieAnimationView loadingView;


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
        subscribeToViewModel();
    }

    private void subscribeToViewModel() {
        settingViewModel = ViewModelProviders.of(this)
                .get(SettingViewModel.class);
    }

    //region clear click
    @OnClick(R.id.clear)
    void onHistoryClearClick(View view) {
        loadingView.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            settingViewModel.deleteHistory();
            loadingView.setVisibility(View.INVISIBLE);
        }, 2000);
    }
    //endregion

    //region share click
    @OnClick(R.id.share)
    void onShareClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Хотите поделится с друзьями?");
        builder.setNegativeButton("Нет", (dialog, which) -> builder.setCancelable(true));
        builder.setPositiveButton("Да", (dialog, which) -> {
            try {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my profile at: https://www.facebook.com/kgz.mail");
                startActivity(
                        Intent.createChooser(shareIntent, "Выбрать")
                );
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        });
        builder.show().create();
    }
    //endregion
}
