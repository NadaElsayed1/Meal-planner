package com.example.mealsplanner.meals_search.presenter;

import com.example.mealsplanner.network.NetworkCallback;

public interface IMealSearchPresenter {
    void searchMeals(String query, NetworkCallback networkCallback);
}
