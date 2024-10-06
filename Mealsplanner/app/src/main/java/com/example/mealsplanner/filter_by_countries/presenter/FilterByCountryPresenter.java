package com.example.mealsplanner.filter_by_countries.presenter;

import com.example.mealsplanner.filter_by_countries.view.IFilterByCountryView;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class FilterByCountryPresenter implements IFilterByCountryPresenter, NetworkCallback {

    private MealRepository mealRepository;
    private IFilterByCountryView view;

    public FilterByCountryPresenter(MealRepository mealRepository, IFilterByCountryView view) {
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
