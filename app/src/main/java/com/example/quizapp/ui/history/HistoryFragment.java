package com.example.quizapp.ui.history;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.base.BaseFragment;
import com.example.quizapp.ui.history.adapter.HistoryAdapter;

import butterknife.BindView;

public class HistoryFragment extends BaseFragment {
    private HistoryViewModel historyViewModel;


    @BindView(R.id.history_recycler)
    RecyclerView historyRecycler;

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
        HistoryAdapter adapter = new HistoryAdapter();
        historyRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        historyRecycler.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
