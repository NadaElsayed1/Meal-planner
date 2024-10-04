package com.example.mealsplanner.meals_categories.presenter;

import com.example.mealsplanner.meals_categories.view.IMealCategoriesFragment;
import com.example.mealsplanner.model.CategoryDTO;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.CategoryNetworkCallBack;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public class MealCategoriesPresenter implements IMealCategoriesPresenter , CategoryNetworkCallBack {
    private MealRepository mealRepository;
    private IMealCategoriesFragment view;

    public MealCategoriesPresenter(MealRepository mealRepository, IMealCategoriesFragment view) {
        this.mealRepository = mealRepository;
        this.view = view;
    }

    @Override
    public void getMealCategories() {
        mealRepository.getMealCategories(this);
    }

    @Override
    public void onSuccessResult(List<CategoryDTO> categories) {
        view.showData(categories);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showErrMsg(errorMsg);
    }
}
