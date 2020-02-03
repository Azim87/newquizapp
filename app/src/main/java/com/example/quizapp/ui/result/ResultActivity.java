package com.example.quizapp.ui.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.example.quizapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {
    private ResultViewModel resultViewModel;

    @BindView(R.id.result_categ)
    TextView resultCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        subscribeToViewModel();
    }

    private void subscribeToViewModel() {
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        resultCategory.setSelected(true);
    }
}
