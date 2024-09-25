package com.example.mealsplanner.meals_by_countries.view;

import com.example.mealsplanner.model.MealDTO;
import java.util.List;

public interface IMealByCountryActivity {
    public void showData(List<MealDTO> show);
    public void showErrMsg(String error);
}
