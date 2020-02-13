package com.example.quizapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.quizapp.data.local.converter.QuestionConverters;
import com.example.quizapp.data.local.converter.TimeStampConverters;

import java.util.Date;
import java.util.List;

@Entity(tableName = "quiz_result")
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "category")
    private int category;

    @ColumnInfo(name = "difficulty")
    private String difficulty;

    @ColumnInfo(name = "questions")
    @TypeConverters(QuestionConverters.class)
    private List<Question> questions;

    @ColumnInfo(name = "correct_answers_amount")
    private int correctAnswersAmount;

    @ColumnInfo(name = "created_at")
    @TypeConverters(TimeStampConverters.class)
    private Date creationDate;

    public QuizResult(int id, int category, String difficulty, List<Question> questions, int correctAnswersAmount, Date creationDate) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.questions = questions;
        this.correctAnswersAmount = correctAnswersAmount;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCorrectAnswersAmount() {
        return correctAnswersAmount;
    }

    public void setCorrectAnswersAmount(int correctAnswersAmount) {
        this.correctAnswersAmount = correctAnswersAmount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
