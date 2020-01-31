package com.example.quizapp.ui.history.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.utils.ShowToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<String> mHistory = new ArrayList<>();
    private Context context;

    public void historyList(List<String> history) {
        mHistory.clear();
        mHistory.addAll(history);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.category.setText("Category: " + mHistory.get(position));
        holder.category.setSelected(true);
        holder.imageMore.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle(mHistory.get(position));
            alertDialog.setMessage("удалить");
            alertDialog.setPositiveButton("Да", (dialog, which) ->
                    ShowToast.message("удалено"));
            alertDialog.setNegativeButton("Нет", (dialog, which) ->
                    ShowToast.message("отмена"));
            alertDialog.create();
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return mHistory.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.history_cat)
        TextView category;
        @BindView(R.id.history_more)
        ImageView imageMore;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
