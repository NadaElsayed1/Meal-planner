package com.example.mealsplanner.model;

import androidx.lifecycle.LiveData;

import com.example.mealsplanner.db.MealDAO;
import com.example.mealsplanner.network.MealService;

import java.util.List;

import retrofit2.Call;

public class MealLocalDataSource {
        private MealService mealService;
        private MealDAO mealDao; // For Room database

        public MealLocalDataSource(MealService service, MealDAO mealDao) {
            this.mealService = service;
            this.mealDao = mealDao;
        }

        public Call<MealResponse> getMealOfTheDay() {
            return mealService.getMealOfTheDay();
        }

        public LiveData<List<MealDTO>> getFavoriteMeals() {
            return mealDao.getAllMeals();  // Fetch from Room database
        }

        public void insertMeal(MealDTO meal) {
            new Thread(() -> mealDao.insertMeal(meal)).start();
        }

        public void deleteMeal(MealDTO meal) {
            new Thread(() -> mealDao.deleteMeal(meal)).start();
        }
    }
