package com.example.mealsplanner.meal_of_the_day.presenter;

import com.example.mealsplanner.meal_of_the_day.view.IMealOfTheDayActivity;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealLocalDataSource;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealOfTheDayPresenter implements NetworkCallback, IMealOfTheDayPresenter {
    private MealLocalDataSource repository;
    private IMealOfTheDayActivity view;

    public MealOfTheDayPresenter(MealLocalDataSource repository, IMealOfTheDayActivity view) {
        this.repository = repository;
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
}
