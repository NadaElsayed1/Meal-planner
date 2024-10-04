package com.example.mealsplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealPlannerDTO;

import java.util.List;

@Dao
public interface MealPlannerDAO {

    @Query("SELECT * FROM meal_planner")
    LiveData<List<MealPlannerDTO>> getAllPlannedMeals();

    @Insert
    void insertMealPlan(MealPlannerDTO mealPlanner);

    @Delete
    void deleteMealPlan(MealPlannerDTO mealPlanner);

    @Query("SELECT * FROM meal_planner WHERE date = :date")
    List<MealPlannerDTO> getMealPlansByDate(String date);

//    @Query("DELETE FROM meal_planner WHERE idMeal = :idMeal")
//    void removeMealFromPlanner(MealPlannerDTO mealPlannerDTO);
}
