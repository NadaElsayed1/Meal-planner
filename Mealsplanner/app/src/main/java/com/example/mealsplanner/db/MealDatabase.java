package com.example.mealsplanner.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mealsplanner.model.MealDTO;

@Database(entities = {MealDTO.class}, version = 1)
public abstract class MealDatabase extends RoomDatabase {
    private static MealDatabase instance;

    public abstract MealDAO mealDao();

    public static synchronized MealDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MealDatabase.class, "meal_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
