package com.example.quizapp.ui.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.quizapp.ui.history.HistoryFragment;
import com.example.quizapp.ui.main.MainFragment;
import com.example.quizapp.ui.settings.SettingsFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case 0:
                fragment = MainFragment.newInstance();
                break;
            case 1:
                fragment = SettingsFragment.newInstance();
                break;
            default:
                fragment = HistoryFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
