package com.example.mealsplanner.meals_by_countries.presenter;

import com.example.mealsplanner.meals_by_countries.view.IMealByCountryView;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealByCountryPresenter implements IMealByCountryPresenter, NetworkCallback {

    private MealRepository mealRepository;
    private IMealByCountryView view;

    public MealByCountryPresenter(MealRepository mealRepository, IMealByCountryView view) {
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
    public void getMealsByCountry(String country) {
        mealRepository.getMealsByCountry(country, this);

    }
}
