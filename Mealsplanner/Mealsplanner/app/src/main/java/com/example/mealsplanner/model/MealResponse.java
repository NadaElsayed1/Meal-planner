package com.example.mealsplanner.model;
import java.util.List;

public class MealResponse {
    private List<MealDTO> meals;

    public MealResponse(List<MealDTO> meals) {
        this.meals = meals;
    }

    public void setMeals(List<MealDTO> meals) {
        this.meals = meals;
    }

    public List<MealDTO> getMeals() {
        return meals;
    }
}
