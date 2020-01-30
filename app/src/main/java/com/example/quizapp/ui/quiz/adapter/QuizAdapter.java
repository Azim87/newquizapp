package com.example.quizapp.ui.quiz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private List<Question> mQuestion = new ArrayList<>();

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mQuestion.size();
    }

    public void setList(List<Question> questionList) {
        mQuestion.clear();
        mQuestion.addAll(questionList);
        notifyDataSetChanged();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Question question) {
        }
    }
}
