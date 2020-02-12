package com.example.quizapp.data;
import androidx.lifecycle.LiveData;

import com.example.quizapp.data.local.IHistoryStorage;
import com.example.quizapp.data.remote.IQuizApiService;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.QuizResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IQuizRepository {
    private IQuizApiService mQuizApiClient;
    private IHistoryStorage mHistoryStorage;
    public QuizRepository(
            IQuizApiService apiService,
            IHistoryStorage historyStorage) {
        mQuizApiClient = apiService;
        mHistoryStorage = historyStorage;
    }

    //region get questions from remote data source
    @Override
    public void getQuizQuestions(int amount, Integer category, String difficulty, String type, QuizCallBack callBack) {
        mQuizApiClient.getQuestions(amount, category, difficulty, type, new QuizCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                if (result != null) {
                    for (int i = 0; i < result.size(); i++) {
                        Question question = result.get(i);
                        result.set(i, shuffleQuestions(question));
                    }
                    callBack.onSuccess(result);
                }
            }
            @Override
            public void onFailure(Exception e) {
                callBack.onFailure(e);
            }
        });
    }
    //endregion

    //region shuffle questions
    private Question shuffleQuestions(Question question) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswers());
        Collections.shuffle(answers);
        question.setAnswers(answers);
        return question;
    }
    //endregion

    //region local data source
    @Override
    public QuizResult getQuizResult(int id) {
        mHistoryStorage.getQuizResult(id);
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        mHistoryStorage.saveQuizResult(quizResult);
        return 0;
    }

    @Override
    public void deleteAll() {
        mHistoryStorage.deleteAll();
    }

    @Override
    public int deleteById(int id) {
        mHistoryStorage.deleteById(id);
        return 0;
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        mHistoryStorage.getAll();
        return null;
    }
    //endregion
}
