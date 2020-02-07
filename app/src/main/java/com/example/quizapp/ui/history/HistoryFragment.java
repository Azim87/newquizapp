package com.example.quizapp.ui.history;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.base.BaseFragment;
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
    private List<String> names;

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
        names = new ArrayList<>();

        adapter.historyList(names);
        if (names.isEmpty()) {
            historyRecycler.setVisibility(View.GONE);
            mainText.setVisibility(View.VISIBLE);
        } else {
            historyRecycler.setVisibility(View.VISIBLE);
            mainText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        subscribeToViewModel();
    }

    private void subscribeToViewModel() {
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
       /* historyViewModel.quizResult.observe(getActivity(), quizResults ->
                adapter.historyList(quizResults));*/
    }

    @Override
    public void onDetailPosition(int position) {
        ShowToast.message("history  " + position);
        HistoryDetailsActivity.start(getContext());
    }
}
