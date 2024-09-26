package com.example.mealsplanner.meals_search.presenter;

import com.example.mealsplanner.meals_search.view.IMealSearchView;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealSearchPresenter implements IMealSearchPresenter , NetworkCallback {

    private MealRemoteDataStructure apiClient;
    private IMealSearchView view;

    public MealSearchPresenter(MealRemoteDataStructure apiClient, IMealSearchView view) {
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
    public void searchMeals(String query) {
        apiClient.searchMeals(query,this);
    }
}