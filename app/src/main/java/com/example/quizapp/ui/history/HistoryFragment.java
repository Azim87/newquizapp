package com.example.quizapp.ui.history;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.base.BaseFragment;
import com.example.quizapp.models.History;
import com.example.quizapp.models.QuizResult;
import com.example.quizapp.ui.detail.HistoryDetailsActivity;
import com.example.quizapp.ui.history.adapter.HistoryAdapter;
import com.example.quizapp.utils.ShowToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment
        implements HistoryAdapter.HistoryDetailListener {
    private HistoryViewModel historyViewModel;
    private HistoryAdapter adapter;

    @BindView(R.id.history_recycler)
    RecyclerView historyRecycler;
    @BindView(R.id.history_main_text)
    TextView mainText;

    public static Fragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void setUpView(View view) {
        initView();
    }

    private void initView() {
        adapter = new HistoryAdapter(this);
        historyRecycler.setLayoutManager(
                new LinearLayoutManager(getContext()));
        historyRecycler.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        getHistoryData();
    }

    private void getHistoryData() {
        historyViewModel.quizResult.observe(getViewLifecycleOwner(), quizHistory ->  {
            Log.d("ololo", "getHistoryData: " + quizHistory);
            adapter.historyList(quizHistory);

            if (quizHistory.isEmpty()) {
                historyRecycler.setVisibility(View.GONE);
                mainText.setVisibility(View.VISIBLE);
            } else {
                historyRecycler.setVisibility(View.VISIBLE);
                mainText.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDetailPosition(int position) {
        HistoryDetailsActivity.start(getContext());
    }
}
