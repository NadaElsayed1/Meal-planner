package com.example.mealsplanner.meals_search.presenter;

import com.example.mealsplanner.meals_search.view.IMealSearchActivity;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealLocalDataSource;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealSearchPresenter implements IMealSearchPresenter , NetworkCallback {

    private MealLocalDataSource repo;
    private IMealSearchActivity view;

    public MealSearchPresenter(MealLocalDataSource repo, IMealSearchActivity view) {
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