package com.example.mealsplanner.meals_by_ingredient.view;
import com.example.mealsplanner.model.MealDTO;
import java.util.List;

public interface IMealByIngredientFragment {
    public void showData(List<MealDTO> show);
    public void showErrMsg(String error);
}
