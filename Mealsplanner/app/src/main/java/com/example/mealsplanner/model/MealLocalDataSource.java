package com.example.mealsplanner.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.mealsplanner.db.MealDAO;
import com.example.mealsplanner.db.MealDatabase;
import com.example.mealsplanner.network.MealService;

import java.util.List;

import retrofit2.Call;

public class MealLocalDataSource {
    //        private MealService mealService;
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



//        public Call<MealResponse> getMealOfTheDay() {
//            return mealService.getMealOfTheDay();
//        }
//
//        public LiveData<List<MealDTO>> getFavoriteMeals() {
//            return mealDao.getAllMeals();  // Fetch from Room database
//        }
