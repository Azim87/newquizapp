package com.example.quizapp.ui.quiz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
        holder.onBind(mQuestion.get(position));
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

    public List<Question> getListPosition() {
        return mQuestion;
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.question_title)
        TextView questionTitle;
        @BindView(R.id.question_1)
        TextView questionTextView1;
        @BindView(R.id.question_2)
        TextView questionTextView2;
        @BindView(R.id.question_3)
        TextView questionTextView3;
        @BindView(R.id.question_4)
        TextView questionTextView4;
        @BindView(R.id.question_true)
        TextView questionTextViewYes;
        @BindView(R.id.question_false)
        TextView questionTextViewNo;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Question question) {
            questionTitle.setText(question.getQuestion());
        }
    }
}
