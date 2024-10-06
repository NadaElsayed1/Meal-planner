package com.example.mealsplanner.model;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.network.CategoryNetworkCallBack;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import retrofit2.Call;

public class MealRepository implements IMealRepository  {
    private static MealRepository repo = null;

    private MealRemoteDataStructure mealRemoteDataStructure;
    private MealLocalDataSource mealLocalDataSource;

    public MealRepository(MealRemoteDataStructure remoteDataSource, MealLocalDataSource localDataSource) {
        this.mealRemoteDataStructure = remoteDataSource;
        this.mealLocalDataSource = localDataSource;
    }


    public static MealRepository getInstance(MealRemoteDataStructure remoteDataSource, MealLocalDataSource localDataSource) {
        if(repo == null) {
            repo = new MealRepository(remoteDataSource, localDataSource);
        }
        return repo;
    }

    @Override
    public void searchMeals(String Query, NetworkCallback networkCallback) {
        mealRemoteDataStructure.searchMeals(Query,networkCallback);
    }

    @Override
    public void getMealsByCategory(String category, NetworkCallback networkCallback) {
        mealRemoteDataStructure.getMealsByCategory(category,networkCallback);
    }


    @Override
    public void getMealsByCountry(String country, NetworkCallback networkCallback) {
        mealRemoteDataStructure.getMealsByCountry(country,networkCallback);
    }

    @Override
    public void getMealOfTheDay( NetworkCallback networkCallback) {
        mealRemoteDataStructure.getMealOfTheDay(networkCallback);

    }
    public void getMealCategories(CategoryNetworkCallBack networkCallback) {
        mealRemoteDataStructure.getMealCategories(networkCallback);
    }

    public void getMealCountries(NetworkCallback networkCallback) {
        mealRemoteDataStructure.getMealCountries(networkCallback);
    }

    public void getMealIngredients(NetworkCallback networkCallback) {
        mealRemoteDataStructure.getMealIngredients(networkCallback);
    }

    public void filterMealsByIngredient(String ingredient, NetworkCallback networkCallback) {
        mealRemoteDataStructure.filterMealsByIngredient(ingredient,networkCallback);
    }

    public void lookupMealById(String mealId, NetworkCallback networkCallback) {
        mealRemoteDataStructure.lookupMealById(mealId,networkCallback);
    }
}

