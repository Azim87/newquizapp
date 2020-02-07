package com.example.quizapp.ui.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.App;
import com.example.quizapp.base.SingleLiveEvent;
import com.example.quizapp.data.IQuizRepository;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.QuizResult;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {
    private IQuizRepository quizRepository = App.iQuizRepository;
    private List<Question> mQuestion;
    private int amount;
    private int category;
    private String difficulty;

    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    MutableLiveData<List<Question>> questionList = new MutableLiveData<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<String> message = new SingleLiveEvent<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();

    void getQuizQuestion(int amount, int category, String difficulty, String type) {
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty;
        quizRepository.getQuizQuestions(amount, category, difficulty, type,
                new IQuizRepository.QuizCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                if (result != null) {
                    mQuestion = result;
                    questionList.setValue(mQuestion);
                    currentQuestionPosition.setValue(0);
                }
            }

            @Override
            public void onFailure(Exception e) {
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

    private void finishQuiz() {
        QuizResult quizResult = new QuizResult(
                0,
                category,
                difficulty,
                mQuestion,
                getCorrectAnswers(),
                new Date()
        );
        int resultId = quizRepository.saveQuizResult(quizResult);
        openResultEvent.setValue(resultId);
        finishEvent.call();
    }

    private int getCorrectAnswers() {
        return 0;
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
