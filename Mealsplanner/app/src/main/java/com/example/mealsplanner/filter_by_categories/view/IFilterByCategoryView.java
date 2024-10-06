package com.example.mealsplanner.filter_by_categories.view;

import com.example.mealsplanner.model.MealDTO;

import java.util.List;

public interface IFilterByCategoryView {
    public void showData(List<MealDTO> show);
    public void showErrMsg(String error);
}
