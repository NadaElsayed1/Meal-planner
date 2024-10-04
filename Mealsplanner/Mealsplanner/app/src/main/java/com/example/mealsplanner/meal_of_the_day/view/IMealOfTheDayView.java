package com.example.mealsplanner.meal_of_the_day.view;

import com.example.mealsplanner.model.MealDTO;

import java.util.List;

public interface IMealOfTheDayView {
    void showData(List<MealDTO> meals);
    void showErrMsg(String error);
}
