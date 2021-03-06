package com.example.quizapp.ui.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.base.SingleLiveEvent;
import com.example.quizapp.data.IQuizRepository;
import com.example.quizapp.data.QuizRepository;
import com.example.quizapp.data.local.HistoryStorage;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.QuizResult;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {
    private HistoryStorage historyStorage = App.historyStorage;
    private IQuizRepository quizRepository = App.iQuizRepository;
    private int amount;
    private int category;
    private String difficulty;
    private List<Question> mQuestion;

    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    MutableLiveData<List<Question>> questionList = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<String> message = new SingleLiveEvent<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();

    private int getCorrectAnswers() {
        int correctAnswers = 0;
        for (Question question : mQuestion) {
            Integer selectedAnswerPosition = question.getSelectedAnswerPosition();
            if (selectedAnswerPosition != null &&
                    selectedAnswerPosition >= 0 &&
                    question.getAnswers().get(selectedAnswerPosition).equals(question.getCorrectAnswer())) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    void finishQuiz() {
        QuizResult quizResult = new QuizResult(
                0,
                category,
                difficulty,
                mQuestion,
                getCorrectAnswers(),
                new Date()
        );
        int resultId = historyStorage.saveQuizResult(quizResult);
        finishEvent.call();
        openResultEvent.setValue(resultId);
        Log.d("ololo", "finishQuiz: " + quizResult.getQuestions());
    }

    void getQuizQuestion(int amount, int category, String difficulty, String type) {
        isLoading.setValue(true);
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty;
        quizRepository.getQuizQuestions(amount, category, difficulty, type,
                new IQuizRepository.QuizCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                if (result != null) {
                    isLoading.setValue(false);
                    mQuestion = result;
                    questionList.setValue(mQuestion);
                    currentQuestionPosition.setValue(0);
                }
            }

            @Override
            public void onFailure(Exception e) {
                isLoading.setValue(false);
                message.setValue(e.getLocalizedMessage());
            }
        });
    }

    void onAnswerPositionClick(int position, int selectedAnswerPosition) {
        if (currentQuestionPosition.getValue() == null || mQuestion == null) {
            return;
        }

        mQuestion.get(position).setSelectedAnswerPosition(selectedAnswerPosition);
        questionList.setValue(mQuestion);

        if (position == mQuestion.size() - 1) {
            finishQuiz();
        } else {
            currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
        }
    }

    void onSkipClick() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            onAnswerPositionClick(currentPosition, -1);
        }
    }

    void onBackPressed() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition == 0) {
                finishEvent.call();
            } else {
                currentQuestionPosition.setValue(currentQuestionPosition.getValue() - 1);
            }
        } else {
            finishEvent.call();
        }
    }
}
