package com.example.quizapp.base;

public interface BaseCallBack<T> {
    void onSeccuss(T result);

    void onFailure(Exception e);
}
