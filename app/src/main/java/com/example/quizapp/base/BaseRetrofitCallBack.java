package com.example.quizapp.base;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseRetrofitCallBack<T> implements Callback<T> {

    public abstract void onSuccess(T results);

    public abstract void onFailure(Exception e);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                onSuccess(response.body());

            } else {
                onFailure(new Exception("Response body is empty " + response.code()));
            }
        } else {
            onFailure(new Exception("Request failed " + response.code()));
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(new Exception(t));
    }
}
