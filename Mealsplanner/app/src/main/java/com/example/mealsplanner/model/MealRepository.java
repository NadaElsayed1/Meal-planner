package com.example.mealsplanner.model;

import androidx.lifecycle.LiveData;

import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.List;

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
}

