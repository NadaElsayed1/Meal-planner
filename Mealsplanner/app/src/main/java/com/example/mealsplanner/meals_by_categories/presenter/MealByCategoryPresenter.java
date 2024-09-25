package com.example.mealsplanner.meals_by_categories.presenter;

import com.example.mealsplanner.meals_by_categories.view.IMealByCategoryActivity;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealLocalDataSource;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;
import java.util.List;

public class MealByCategoryPresenter implements IMealByCategoryPresenter , NetworkCallback {

    private MealRemoteDataStructure apiClient;
    private IMealByCategoryActivity view;

    public MealByCategoryPresenter(MealRemoteDataStructure apiClient, IMealByCategoryActivity view) {
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
    public void getMealsByCategory(String category, NetworkCallback networkCallback) {
        apiClient.getMealsByCategory(category,this);
    }
}
