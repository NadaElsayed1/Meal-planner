package com.example.mealsplanner.meals_by_countries.presenter;

import com.example.mealsplanner.meals_by_countries.view.IMealByCountryActivity;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealLocalDataSource;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealByCountryPresenter implements IMealByCountryPresenter, NetworkCallback {

    private MealLocalDataSource repo;
    private IMealByCountryActivity view;

    public MealByCountryPresenter(MealLocalDataSource repo, IMealByCountryActivity view) {
        this.repo = repo;
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
