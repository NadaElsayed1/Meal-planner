package com.example.mealsplanner.meals_categories.view;

import com.example.mealsplanner.model.CategoryDTO;
import com.example.mealsplanner.model.MealDTO;
import java.util.List;

public interface IMealCategoriesFragment {
    void showData(List<CategoryDTO> categories);
    void showErrMsg(String error);
}
