package com.example.mealsplanner.model;
import com.example.mealsplanner.network.CategoryNetworkCallBack;
import com.example.mealsplanner.network.NetworkCallback;

public interface IMealRepository {
    public void searchMeals(String Query, NetworkCallback networkCallback);
    public void getMealsByCategory(String category, NetworkCallback networkCallback);
    public void getMealsByCountry(String country, NetworkCallback networkCallback);
    public void getMealOfTheDay(NetworkCallback networkCallback);
    public void getMealCategories(CategoryNetworkCallBack networkCallback);
    public void getMealCountries(NetworkCallback networkCallback);
    public void getMealIngredients(NetworkCallback networkCallback);
    public void filterMealsByIngredient(String ingredient, NetworkCallback networkCallback);
    public void getLatestMeals(NetworkCallback networkCallback);
    public void lookupMealById(String mealId, NetworkCallback networkCallback);
    }
