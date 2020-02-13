package com.example.quizapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.quizapp.models.QuizResult;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    long insert(QuizResult quizResult);

    @Query("SELECT * FROM quiz_result WHERE id=:id")
    QuizResult get(int id);

    @Query("DELETE FROM quiz_result WHERE id=:id")
    int delete(int id);

    @Query("DELETE FROM quiz_result")
    int deleteAll();

    @Query("SELECT * FROM quiz_result  ORDER BY ID DESC")
    LiveData<List<QuizResult>> getAll();
}
