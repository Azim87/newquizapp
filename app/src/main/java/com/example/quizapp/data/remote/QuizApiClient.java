package com.example.quizapp.data.remote;

import com.example.quizapp.data.remote.model.QuizQuestionsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiService {
    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://opentdb.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private QuizApiService quizApiService = retrofit.create(QuizApiService.class;

    @Override
    public void getQuestions(int amount, Integer category, String difficulty, QuestionCallBack questionCallBack) {
        Call<QuizQuestionsResponse> call = quizApiService.getQuestions(
                amount, category, difficulty
        );
        call.enqueue(new Callback<QuizQuestionsResponse>() {
            @Override
            public void onResponse(Call<QuizQuestionsResponse> call, Response<QuizQuestionsResponse> response) {

            }

            @Override
            public void onFailure(Call<QuizQuestionsResponse> call, Throwable t) {

            }
        });
    }

    private interface QuizApiService {
        @GET("api.php")
        Call<QuizQuestionsResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") Integer category,
                @Query("difficulty") String difficulty
        );
    }
}
