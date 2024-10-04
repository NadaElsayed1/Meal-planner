package com.example.mealsplanner.lookup_meal_by_id.presenter;

import com.example.mealsplanner.lookup_meal_by_id.view.IMealByIDFragment;
import com.example.mealsplanner.meals_by_ingredient.view.IMealByIngredientFragment;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealByIDPresenter implements IMealByIDPresenter , NetworkCallback {

    private MealRepository mealRepository;
    private IMealByIDFragment view;

    public MealByIDPresenter(MealRepository mealRepository, IMealByIDFragment view) {
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
    public void lookupMealById(String mealId) {
        mealRepository.lookupMealById(mealId, this);

    }
}