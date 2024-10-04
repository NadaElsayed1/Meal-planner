package com.example.mealsplanner.network;

import com.example.mealsplanner.model.MealDTO;
import java.util.List;

public interface NetworkCallback {
    public void onSuccessResult(List<MealDTO> meals);
    public void onFailureResult(String errorMsg);
}
