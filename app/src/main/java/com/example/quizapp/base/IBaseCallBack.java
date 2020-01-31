package com.example.quizapp.base;

public interface IBaseCallBack<T> {
    void onSuccess(T result);

    void onFailure(Exception e);
}
