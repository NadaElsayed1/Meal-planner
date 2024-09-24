package com.example.mealsplanner.all_meals.view;

import com.example.mealsplanner.model.MealDTO;

public interface IMealOfTheDayActivity {
    void displayMealOfTheDay(MealDTO meal);
    void showError(String message);
    void showSuccessMessage(String message);
    void onMealClick(MealDTO meal); // Add meal click handler
}
