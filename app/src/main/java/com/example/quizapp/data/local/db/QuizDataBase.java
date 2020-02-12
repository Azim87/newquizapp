package com.example.quizapp.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quizapp.data.local.dao.HistoryDao;
import com.example.quizapp.models.QuizResult;


@Database(
        entities = {QuizResult.class},
        exportSchema = false,
        version = 1
)
public abstract class QuizDataBase extends RoomDatabase {
    public abstract HistoryDao getHistoryDao();
}
