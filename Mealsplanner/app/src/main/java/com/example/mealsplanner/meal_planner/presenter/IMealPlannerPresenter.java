package com.example.mealsplanner.meal_planner.presenter;

import com.example.mealsplanner.model.MealPlannerDTO;

public interface IMealPlannerPresenter {
    void removeMealFromPlanner(MealPlannerDTO mealPlannerDTO);
    public void loadMealPlansForDate(String date);
}
