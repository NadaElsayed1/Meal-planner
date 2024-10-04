package com.example.mealsplanner.meals_by_ingredient.presenter;

import com.example.mealsplanner.meals_by_ingredient.view.IMealByIngredientFragment;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;
import java.util.List;

public class MealByIngredientPresenter implements IMealByIngredientPresenter , NetworkCallback {

    private MealRepository mealRepository;
    private IMealByIngredientFragment view;

    public MealByIngredientPresenter(MealRepository mealRepository, IMealByIngredientFragment view) {
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