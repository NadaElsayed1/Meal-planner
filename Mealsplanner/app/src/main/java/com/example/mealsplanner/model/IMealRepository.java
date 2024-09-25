package com.example.mealsplanner.model;

import androidx.lifecycle.LiveData;

import com.example.mealsplanner.db.MealDatabase;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

public interface IMealRepository {
    public void searchMeals(String Query, NetworkCallback networkCallback);
    public void getMealsByCategory(String category, NetworkCallback networkCallback);
    public void getMealsByCountry(String country, NetworkCallback networkCallback);
    public void getMealOfTheDay(NetworkCallback networkCallback);
}
