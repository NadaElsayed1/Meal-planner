package com.example.mealsplanner.meals_by_categories.presenter;

import com.example.mealsplanner.meals_by_categories.view.IMealByCategoryActivity;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealLocalDataSource;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;
import java.util.List;

public class MealByCategoryPresenter implements IMealByCategoryPresenter , NetworkCallback {

    private MealLocalDataSource repo;
    private IMealByCategoryActivity view;

    public MealByCategoryPresenter(MealLocalDataSource repo, IMealByCategoryActivity view) {
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
