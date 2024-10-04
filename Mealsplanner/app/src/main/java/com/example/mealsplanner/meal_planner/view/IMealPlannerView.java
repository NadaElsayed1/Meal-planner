package com.example.mealsplanner.meal_planner.view;
import com.example.mealsplanner.model.MealPlannerDTO;

import java.util.List;

public interface IMealPlannerView {
    void RemovePlannedItem(MealPlannerDTO mealPlannerDTO);
    void showMealPlans(List<MealPlannerDTO> mealPlannerList);
}
