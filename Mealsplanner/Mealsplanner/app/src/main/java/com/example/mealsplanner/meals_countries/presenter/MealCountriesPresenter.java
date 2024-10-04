package com.example.mealsplanner.meals_countries.presenter;

import com.example.mealsplanner.meals_by_categories.presenter.IMealByCategoryPresenter;
import com.example.mealsplanner.meals_by_categories.view.IMealByCategoryView;
import com.example.mealsplanner.meals_countries.view.IMealCountriesFragment;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealCountriesPresenter implements IMealCountriesPresenter, NetworkCallback{

        private MealRepository mealRepository;
        private IMealCountriesFragment view;

    public MealCountriesPresenter(MealRepository mealRepository, IMealCountriesFragment view) {
            this.mealRepository = mealRepository;
            this.view = view;
        }

        @Override
        public void onSuccessResult(List< MealDTO > meals) {
            view.showData(meals);
        }

        @Override
        public void onFailureResult(String errorMsg) {
            view.showErrMsg(errorMsg);
        }

        @Override
    public void getMealCountries() {
            mealRepository.getMealCountries(this);
        }

}
