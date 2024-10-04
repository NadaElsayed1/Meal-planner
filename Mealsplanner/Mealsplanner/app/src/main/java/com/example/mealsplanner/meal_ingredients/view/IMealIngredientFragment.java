package com.example.mealsplanner.meal_ingredients.view;

import com.example.mealsplanner.model.MealDTO;
import java.util.List;

public interface IMealIngredientFragment {
    public void showData(List<MealDTO> show);
    public void showErrMsg(String error);
}
