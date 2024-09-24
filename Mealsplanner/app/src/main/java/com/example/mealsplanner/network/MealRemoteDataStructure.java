package com.example.mealsplanner.network;

import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealResponse;
import com.example.mealsplanner.network.MealService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataStructure {

    private static final String BASE_URL = "https://www.themealdb.com/";
    private static MealRemoteDataStructure instance = null;
    private MealService mealService;

    private MealRemoteDataStructure() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mealService = retrofit.create(MealService.class);
    }

    public static MealRemoteDataStructure getInstance() {
        if (instance == null) {
            instance = new MealRemoteDataStructure();
        }
        return instance;
    }

    public void getMealOfTheDay(NetworkCallback networkCallback) {
        Call<MealResponse> call = mealService.getMealOfTheDay();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MealDTO> meals = response.body().getMeals();
                    networkCallback.onSuccessResult(meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());
            }
        });
    }

    public void getMealsByCategory(String category, NetworkCallback networkCallback) {
        Call<MealResponse> call = mealService.getMealsByCategory(category);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MealDTO> meals = response.body().getMeals();
                    networkCallback.onSuccessResult(meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());
            }
        });
    }

    public void getMealsByCountry(String country, NetworkCallback networkCallback) {
        Call<MealResponse> call = mealService.getMealsByCountry(country);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MealDTO> meals = response.body().getMeals();
                    networkCallback.onSuccessResult(meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());
            }
        });
    }

    public void searchMeals(String searchQuery, NetworkCallback networkCallback) {
        Call<MealResponse> call = mealService.searchMeals(searchQuery);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MealDTO> meals = response.body().getMeals();
                    networkCallback.onSuccessResult(meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());
            }
        });
    }
}
