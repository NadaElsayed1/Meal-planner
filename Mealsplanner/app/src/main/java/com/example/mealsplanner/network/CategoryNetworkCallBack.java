package com.example.mealsplanner.network;

import com.example.mealsplanner.model.CategoryDTO;
import com.example.mealsplanner.model.MealDTO;

import java.util.List;

public interface CategoryNetworkCallBack {
    public void onSuccessResult(List<CategoryDTO> categories);
    public void onFailureResult(String errorMsg);
}
