package com.example.quizapp.models;

import java.util.Date;
import java.util.List;

public class QuizResult {

    private int id;
    private int category;
    private String difficulty;
    private List<Question> questions;
    private int correctAnswers;
    private Date creationDate;

    public QuizResult(int id, int category, String difficulty, List<Question> questions, int correctAnswers, Date creationDate) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.questions = questions;
        this.correctAnswers = correctAnswers;
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

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
 }
