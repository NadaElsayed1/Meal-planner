package com.example.mealsplanner.meals_by_countries.presenter;

import com.example.mealsplanner.network.NetworkCallback;

public interface IMealByCountryPresenter {
    void getMealsByCountry(String country, NetworkCallback networkCallback);
}
