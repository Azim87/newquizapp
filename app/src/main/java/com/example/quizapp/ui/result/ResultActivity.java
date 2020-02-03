package com.example.quizapp.ui.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {
    private ResultViewModel resultViewModel;

    @BindView(R.id.result_categ) TextView resultCategory;
    @BindView(R.id.result_finish) Button finishButton;

    public static void start(Context context) {
        Intent resultIntent = new Intent(context, ResultActivity.class);
        context.startActivity(resultIntent);
    }

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

    @OnClick(R.id.result_finish)
    void onFinishClick(View view) {
        finish();
    }
}
