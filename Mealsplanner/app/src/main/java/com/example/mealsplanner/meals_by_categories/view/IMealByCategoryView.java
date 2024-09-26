package com.example.mealsplanner.meals_by_categories.view;

import com.example.mealsplanner.model.MealDTO;

import java.util.List;

public interface IMealByCategoryView {
    public void showData(List<MealDTO> show);
    public void showErrMsg(String error);
}
