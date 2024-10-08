package com.example.mealsplanner.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealsplanner.model.MealDTO;

import java.util.List;

public class MealLocalDataSource {
    private Context context;
    private MealDAO mealDao; // For Room database
    private LiveData<List<MealDTO>> storedMeals;
    private static MealLocalDataSource productLocalDataSourcse = null;


    private MealLocalDataSource(Context _context) {
        this.context = _context;
        MealDatabase db = MealDatabase.getInstance(context.getApplicationContext());
        mealDao = db.getMealDao();
        storedMeals = mealDao.getAllMeals();
    }
    public LiveData<List<MealDTO>> getAllMeals()
    {return  storedMeals;}

    public static MealLocalDataSource getInstance(Context _context)
    {
        if(productLocalDataSourcse == null)
        {
            productLocalDataSourcse = new MealLocalDataSource(_context);
        }
        return productLocalDataSourcse;
    }


    public void delete(MealDTO mealDTO)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.deleteMeal(mealDTO);
            }
        }).start();
    }

    public void insert(MealDTO mealDTO)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDao.insertMeal(mealDTO);
            }
        }).start();
    }
}
