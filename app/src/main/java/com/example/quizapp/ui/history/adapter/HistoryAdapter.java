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

import com.example.quizapp.App;
import com.example.quizapp.R;
import com.example.quizapp.models.History;
import com.example.quizapp.models.QuizResult;
import com.example.quizapp.utils.ShowToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<History> mHistory = new ArrayList<>();
    private HistoryDetailListener listener;
    private Context context;

    public HistoryAdapter(HistoryDetailListener listener) {
        this.listener = listener;
    }

    public void historyList(List<History> history) {
        mHistory.clear();
        mHistory.addAll(history);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).
                inflate(
                        R.layout.item_history,
                        parent,
                        false
                );
        return new HistoryViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.bind(mHistory.get(position));
    }

    @Override
    public int getItemCount() {
        return mHistory.size();
    }

    public void deleteById(int id){
        App.historyStorage.deleteById(id);
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.history_cat)
        TextView category;

        @BindView(R.id.history_more)
        ImageView imageMore;
        private HistoryDetailListener detailListener;

        public HistoryViewHolder(@NonNull View itemView, HistoryDetailListener listener) {
            super(itemView);
            detailListener = listener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v ->
                    detailListener.onDetailPosition(getAdapterPosition()));
        }

        private void bind(History result) {
            category.setText("Category: " + result.getCategory());
            category.setSelected(true);
            imageMore.setOnClickListener(v -> {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setMessage("удалить");
                alertDialog
                        .setPositiveButton("Да", (dialog, which) ->
                        ShowToast.message("удалено"));
                alertDialog
                        .setNegativeButton("Нет", (dialog, which) ->
                        ShowToast.message("отмена"));
                alertDialog.create();
                alertDialog.show();
            });
        }
    }

    public interface HistoryDetailListener {
        void onDetailPosition(int position);
    }
}
