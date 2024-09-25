package com.example.mealsplanner.meals_by_categories.presenter;

import com.example.mealsplanner.network.NetworkCallback;

public interface IMealByCategoryPresenter {
    void getMealsByCategory(String category, NetworkCallback networkCallback);
}
