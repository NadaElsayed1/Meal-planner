package com.example.mealsplanner.meals_search.presenter;

import com.example.mealsplanner.meals_search.view.IMealSearchView;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealSearchPresenter implements IMealSearchPresenter , NetworkCallback {

    private MealRepository mealRepository;
    private IMealSearchView view;

    public MealSearchPresenter(MealRepository mealRepository, IMealSearchView view) {
        this.mealRepository = mealRepository;
        this.view = view;
    }
    public void searchByCategory(String category){
        mealRepository.getMealsByCategory(category,this);
    }

    public void searchByCountry(String country){
        mealRepository.getMealsByCountry(country,this);
    }

    public void searchByIngrediant(String ingrediant){
        mealRepository.filterMealsByIngredient( ingrediant,this);
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
        mealRepository.searchMeals(query,this);
    }
}