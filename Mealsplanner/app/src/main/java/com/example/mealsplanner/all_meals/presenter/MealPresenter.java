package com.example.mealsplanner.all_meals.presenter;

import com.example.mealsplanner.all_meals.view.IMealOfTheDayActivity;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealLocalDataSource; // Fixed typo
import com.example.mealsplanner.model.MealResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealPresenter {

    private IMealOfTheDayActivity mealView;  // View interface
    private MealLocalDataSource mealRepository; // Fixed typo

    public MealPresenter(IMealOfTheDayActivity view, MealLocalDataSource repository) { // Fixed typo
        this.mealView = view;
        this.mealRepository = repository;
    }

    public void fetchMealOfTheDay() {
        mealRepository.getMealOfTheDay().enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mealView.displayMealOfTheDay(response.body().getMeals().get(0));
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                mealView.showError(t.getMessage());
            }
        });
    }

    public void addMealToFavorites(MealDTO meal) {
        mealRepository.insertMeal(meal);
        mealView.showSuccessMessage("Meal added to favorites");
    }

    public void removeMealFromFavorites(MealDTO meal) {
        mealRepository.deleteMeal(meal);
        mealView.showSuccessMessage("Meal removed from favorites");
    }
}
