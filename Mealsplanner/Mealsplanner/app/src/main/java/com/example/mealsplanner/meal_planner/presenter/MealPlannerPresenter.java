package com.example.mealsplanner.meal_planner.presenter;

import com.example.mealsplanner.db.MealPlannerLocalDataSource;
import com.example.mealsplanner.meal_planner.view.IMealPlannerView;
import com.example.mealsplanner.model.MealPlannerDTO;

import java.util.List;


public class MealPlannerPresenter implements IMealPlannerPresenter {
    public MealPlannerLocalDataSource repo;
    private IMealPlannerView view;

    public MealPlannerPresenter(IMealPlannerView view,MealPlannerLocalDataSource mealPlannerLocalDataSource) {
        this.repo = mealPlannerLocalDataSource;
        this.view = view;
    }


    @Override
    public void removeMealFromPlanner(MealPlannerDTO mealPlannerDTO) {
        repo.deleteMealPlanned(mealPlannerDTO);
        view.RemovePlannedItem(mealPlannerDTO);
    }

    @Override
    public void loadMealPlansForDate(String date) {
        List<MealPlannerDTO> mealPlannerList = repo.getMealPlansByDate(date);
        view.showMealPlans(mealPlannerList);
    }
}
