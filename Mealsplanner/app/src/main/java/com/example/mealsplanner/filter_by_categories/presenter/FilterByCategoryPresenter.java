package com.example.mealsplanner.filter_by_categories.presenter;

import com.example.mealsplanner.filter_by_categories.view.IFilterByCategoryView;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.NetworkCallback;
import java.util.List;

public class FilterByCategoryPresenter implements IFilterByCategoryPresenter, NetworkCallback {

    private MealRepository mealRepository;
    private IFilterByCategoryView view;

    public FilterByCategoryPresenter(MealRepository mealRepository, IFilterByCategoryView view) {
        this.mealRepository = mealRepository;
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
    public void getMealsByCategory(String category) {
        mealRepository.getMealsByCategory(category,this);
    }

}
