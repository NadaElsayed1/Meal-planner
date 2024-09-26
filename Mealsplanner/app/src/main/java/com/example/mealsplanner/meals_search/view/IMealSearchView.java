package com.example.mealsplanner.meals_search.view;

import com.example.mealsplanner.model.MealDTO;

import java.util.List;

public interface IMealSearchView {
    public void showData(List<MealDTO> show);
    public void showErrMsg(String error);
}
