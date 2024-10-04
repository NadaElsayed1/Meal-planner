package com.example.mealsplanner.lookup_meal_by_id.view;
import com.example.mealsplanner.model.MealDTO;
import java.util.List;

public interface IMealByIDFragment {
    public void showData(List<MealDTO> show);
    public void showErrMsg(String error);
}
