package com.example.mealsplanner.meal_of_the_day.view;

import com.example.mealsplanner.model.MealDTO;

import java.util.List;

public interface IMealOfTheDayActivity {
    public void showData(List<MealDTO> show);
    public void showErrMsg(String error);
}
