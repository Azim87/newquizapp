package com.example.quizapp.ui.quiz.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.models.EType;
import com.example.quizapp.models.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {
    private List<Question> mQuestion = new ArrayList<>();
    private OnQuestionClickListener questionClickListener;

    public QuizAdapter(OnQuestionClickListener questionClickListener) {
        this.questionClickListener = questionClickListener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false);
        return new QuizViewHolder(view, questionClickListener);
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

    public class QuizViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.question_title) TextView questionTitle;
        @BindView(R.id.question_1) TextView questionTextView1;
        @BindView(R.id.question_2) TextView questionTextView2;
        @BindView(R.id.question_3) TextView questionTextView3;
        @BindView(R.id.question_4) TextView questionTextView4;
        @BindView(R.id.question_true) TextView questionTextViewYes;
        @BindView(R.id.question_false) TextView questionTextViewNo;
        @BindView(R.id.container_1) LinearLayout containerMultiple;
        @BindView(R.id.container_2) LinearLayout containerBoolean;
        private OnQuestionClickListener listener;
        private Question question;

        public QuizViewHolder(@NonNull View itemView, OnQuestionClickListener listener) {
            super(itemView);
            this.listener = listener;
            ButterKnife.bind(this, itemView);
            questionTextView1.setOnClickListener(this);
            questionTextView2.setOnClickListener(this);
            questionTextView3.setOnClickListener(this);
            questionTextView4.setOnClickListener(this);
            questionTextViewYes.setOnClickListener(this);
            questionTextViewNo.setOnClickListener(this);
        }

        public void onBind(Question question) {
            this.question = question;
            questionTitle.setText(Html.fromHtml(question.getQuestion()));
            if (question.getType().equals(EType.MULTIPLE)) {
                showMultipleQuestionType(question);
                hideBooleanType();
            } else {
                hideMultipleType();
            }
            if (question.getType().equals(EType.BOOLEAN)) {
                if (question.getCorrectAnswer().equals("true")) {
                    questionTextViewYes.setText("Yes");
                } else {
                    questionTextViewNo.setText("No");
                }
            }
            if (question.getSelectedAnswerPosition() != null) {
                enableQuestionViews(false);
            } else {
                enableQuestionViews(true);
                resetQuestionBackgroundColor();
            }
        }

        private void showMultipleQuestionType(Question question) {
            questionTextView1.setText(Html.fromHtml(question.getAnswers().get(0)));
            questionTextView2.setText(Html.fromHtml(question.getAnswers().get(1)));
            questionTextView3.setText(Html.fromHtml(question.getAnswers().get(2)));
            questionTextView4.setText(Html.fromHtml(question.getAnswers().get(3)));
        }

        private void hideBooleanType() {
            containerMultiple.setVisibility(View.VISIBLE);
            containerBoolean.setVisibility(View.INVISIBLE);
        }

        private void hideMultipleType() {
            containerMultiple.setVisibility(View.INVISIBLE);
            containerBoolean.setVisibility(View.VISIBLE);
        }

        private void setSelectedQuestionColor(Question question, TextView textView) {
            if (question.getAnswers().get(question.getSelectedAnswerPosition()).equals(question.getCorrectAnswer())) {
                textView.setBackgroundResource(R.drawable.question_true_style);
                textView.setTextColor(Color.WHITE);
            } else {
                textView.setBackgroundResource(R.drawable.question_false_style);
                textView.setTextColor(Color.WHITE);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.question_1:
                case R.id.question_true:
                    listener.onAnswerClick(getAdapterPosition(), 0);
                    setSelectedQuestionColor(question, questionTextView1);
                    setSelectedQuestionColor(question, questionTextViewYes);
                    break;
                case R.id.question_2:
                case R.id.question_false:
                    listener.onAnswerClick(getAdapterPosition(), 1);
                    setSelectedQuestionColor(question, questionTextView2);
                    setSelectedQuestionColor(question, questionTextViewNo);
                    break;
                case R.id.question_3:
                    listener.onAnswerClick(getAdapterPosition(), 2);
                    setSelectedQuestionColor(question, questionTextView3);
                    break;
                case R.id.question_4:
                    listener.onAnswerClick(getAdapterPosition(), 3);
                    setSelectedQuestionColor(question, questionTextView4);
                    break;
            }
        }

        @SuppressLint("ResourceAsColor")
        private void resetQuestionBackgroundColor() {
            questionTextView1.setBackgroundResource(R.drawable.quiestion_textview_style);
            questionTextView2.setBackgroundResource(R.drawable.quiestion_textview_style);
            questionTextView3.setBackgroundResource(R.drawable.quiestion_textview_style);
            questionTextView4.setBackgroundResource(R.drawable.quiestion_textview_style);
            questionTextViewYes.setBackgroundResource(R.drawable.quiestion_textview_style);
            questionTextViewNo.setBackgroundResource(R.drawable.quiestion_textview_style);
            questionTextView1.setTextColor(R.color.colorQuestion);
            questionTextView2.setTextColor(R.color.colorQuestion);
            questionTextView3.setTextColor(R.color.colorQuestion);
            questionTextView4.setTextColor(R.color.colorQuestion);
            questionTextViewYes.setTextColor(R.color.colorQuestion);
            questionTextViewNo.setTextColor(R.color.colorQuestion);
        }

        private void enableQuestionViews(boolean enabled) {
            questionTextView1.setEnabled(enabled);
            questionTextView2.setEnabled(enabled);
            questionTextView3.setEnabled(enabled);
            questionTextView4.setEnabled(enabled);
            questionTextViewYes.setEnabled(enabled);
            questionTextViewNo.setEnabled(enabled);
        }
    }

    public interface OnQuestionClickListener {
        void onAnswerClick(int questionPosition, int answerPosition);
    }
}
