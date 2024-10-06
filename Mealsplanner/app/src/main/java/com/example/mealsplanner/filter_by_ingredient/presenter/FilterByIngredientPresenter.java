package com.example.mealsplanner.filter_by_ingredient.presenter;

import com.example.mealsplanner.filter_by_ingredient.view.IFilterByIngredientFragment;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.NetworkCallback;
import java.util.List;

public class FilterByIngredientPresenter implements IFilterByIngredientPresenter, NetworkCallback {

    private MealRepository mealRepository;
    private IFilterByIngredientFragment view;

    public FilterByIngredientPresenter(MealRepository mealRepository, IFilterByIngredientFragment view) {
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
    public void filterMealsByIngredient(String ingredient) {
        mealRepository.filterMealsByIngredient(ingredient, this);

    }
}