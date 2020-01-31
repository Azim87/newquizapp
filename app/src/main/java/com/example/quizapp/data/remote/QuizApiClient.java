package com.example.quizapp.data.remote;

import com.example.quizapp.base.BaseRetrofitCallBack;
import com.example.quizapp.data.IQuizRepository;
import com.example.quizapp.data.remote.model.QuizQuestionsResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiService {

    public Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://opentdb.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    private QuizApiService quizApiService = retrofit.create(QuizApiService.class);

    @Override
    public void getQuestions(int amount, Integer category, String difficulty,
                             IQuizRepository.QuizCallBack callBack) {
        Call<QuizQuestionsResponse> call = quizApiService.getQuestions(
                amount,
                category,
                difficulty);
        call.enqueue(new BaseRetrofitCallBack<QuizQuestionsResponse>() {
            @Override
            public void onSuccess(QuizQuestionsResponse results) {
                callBack.onSuccess(results.getResults());
            }

            @Override
            public void onFailure(Exception e) {
                callBack.onFailure(new Exception(e.getMessage()));
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
