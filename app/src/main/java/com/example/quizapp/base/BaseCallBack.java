package com.example.quizapp.base;

public interface BaseCallBack<T> {
    void onSuccess(T result);

    void onFailure(Exception e);
}
