package com.example.quizapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.quizapp.data.local.dao.HistoryDao;
import com.example.quizapp.models.History;
import com.example.quizapp.models.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    private HistoryDao historyDao;

    public HistoryStorage(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }
    @Override
    public QuizResult getQuizResult(int id) {
        return historyDao.get(id);
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return Transformations.map(getAll(), quizResult -> {
            ArrayList<History> histories = new ArrayList<>();

            for (QuizResult result : quizResult) {
                histories.add(new History(
                        result.getId(),
                        result.getCorrectAnswersAmount(),
                        result.getCategory(),
                        result.getQuestions().get(0).getDifficulty(),
                        result.getCreationDate()
                ));
            }
            return histories;
        });
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return (int) historyDao.insert(quizResult);
    }

    @Override
    public int deleteAll() {
        return historyDao.deleteAll();
    }

    @Override
    public int deleteById(int id) {
        return historyDao.delete(id);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return historyDao.getAll();
    }
}
