package com.example.mealsplanner.filter_by_ingredient.view;
import com.example.mealsplanner.model.MealDTO;
import java.util.List;

public interface IFilterByIngredientFragment {
    public void showData(List<MealDTO> show);
    public void showErrMsg(String error);
}
