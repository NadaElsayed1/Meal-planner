package com.example.mealsplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mealsplanner.model.MealDTO;

import java.util.List;

@Dao
public interface MealDAO {

    @Query("SELECT * FROM meals_table")
    LiveData<List<MealDTO>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(MealDTO meal);

    @Delete
    void deleteMeal(MealDTO meal);
}
