package com.example.mealsplanner.detailed_meals.presenter;

import android.util.Log;

import com.example.mealsplanner.detailed_meals.view.MealDetailsActivity;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealDetailsPresenter implements NetworkCallback {
    private MealRemoteDataStructure apiClient;
    private MealDetailsActivity view;

    public MealDetailsPresenter(MealRemoteDataStructure apiClient, MealDetailsActivity view) {
        this.apiClient = apiClient;
        this.view = view;
    }

    @Override
    public void onSuccessResult(List<MealDTO> meals) {
        if (meals != null && !meals.isEmpty()) {
            view.updateMealDetails(meals.get(0));
        }
    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i("TAG", "onFailureResult: ");
    }

    public void lookupMealById(String mealId) {
        apiClient.lookupMealById(mealId, this);
    }
}
