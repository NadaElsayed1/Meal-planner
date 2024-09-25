package com.example.mealsplanner.meal_of_the_day.presenter;

import com.example.mealsplanner.meal_of_the_day.view.IMealOfTheDayActivity;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealOfTheDayPresenter implements NetworkCallback, IMealOfTheDayPresenter {
    private MealRemoteDataStructure apiClient;
    private IMealOfTheDayActivity view;

    public MealOfTheDayPresenter(MealRemoteDataStructure apiClient, IMealOfTheDayActivity view) {
        this.apiClient = apiClient;
        this.view = view;
    }

    @Override
    public void onSuccessResult(List<MealDTO> meals) {
        view.showData(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showErrMsg(errorMsg);
    }

    @Override
    public void getMealOfTheDay() {
        apiClient.getMealOfTheDay(this);
    }
}
