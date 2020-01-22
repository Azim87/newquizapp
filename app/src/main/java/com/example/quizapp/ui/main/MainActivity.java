package com.example.quizapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.quizapp.R;
import com.example.quizapp.ui.main.adapter.ViewPagerAdapter;
import com.example.quizapp.utils.ShowToast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private boolean doublePressBackToExit = false;
    private MainViewModel mainViewModel;

    @BindView(R.id.main_bottom_nav)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.main_view_pager)
    ViewPager viewPager;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        ButterKnife.bind(this);
        setUpView();
        getMenuId();
    }

    private void setUpView() {
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int page = 0;
        switch (menuItem.getItemId()) {

            case R.id.bottom_nav_history:
                page = 1;
                break;
            case R.id.bottom_nav_settings:
                page = 2;
                break;
        }
        viewPager.setCurrentItem(page);
        return true;
    }

    private void getMenuId() {
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                int selectedItem = R.id.bottom_nav_quiz;
                switch (position) {
                    case 1:
                        selectedItem = R.id.bottom_nav_history;
                        break;
                    case 2:
                        selectedItem = R.id.bottom_nav_settings;
                        break;
                }
                bottomNavigationView.setSelectedItemId(selectedItem);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doublePressBackToExit) {
            super.onBackPressed();
            return;
        }

        doublePressBackToExit = true;
        ShowToast.message("Press again to exit");
        new Handler().postDelayed(() ->
                doublePressBackToExit = false, 2000);
    }
}
