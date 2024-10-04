package com.example.mealsplanner.meal_ingredients.presenter;

import com.example.mealsplanner.meal_ingredients.view.IMealIngredientFragment;
import com.example.mealsplanner.meals_countries.view.IMealCountriesFragment;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealIngredientPresenter implements IMealIngredientPresenter , NetworkCallback {

    private MealRepository mealRepository;
    private IMealIngredientFragment view;

    public MealIngredientPresenter(MealRepository mealRepository, IMealIngredientFragment view) {
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
    public void getMealIngredients() {
        mealRepository.getMealIngredients(this);
    }
}
