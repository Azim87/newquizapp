package com.example.quizapp.models;

import java.util.Date;

public class History {
    private int id;
    private int correctAnswers;
    private int category;
    private String difficulty;
    private Date creationDate;

    public History(int id, int correctAnswers, int category, String difficulty, Date creationDate) {
        this.id = id;
        this.correctAnswers = correctAnswers;
        this.category = category;
        this.difficulty = difficulty;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
