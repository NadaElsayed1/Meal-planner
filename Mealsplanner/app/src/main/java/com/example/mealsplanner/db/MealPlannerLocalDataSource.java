package com.example.mealsplanner.db;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.mealsplanner.model.MealPlannerDTO;
import java.util.List;

public class MealPlannerLocalDataSource {
    private Context context;
    private MealPlannerDAO mealPlannerDAO;
    private LiveData<List<MealPlannerDTO>> storedPlannedMeals;
    private static MealPlannerLocalDataSource mealPlannerLocalDataSource = null;

    private MealPlannerLocalDataSource(Context context) {
        this.context = context;
        MealDatabase db = MealDatabase.getInstance(context.getApplicationContext());
        mealPlannerDAO = db.getMealPlannerDao();
        storedPlannedMeals = mealPlannerDAO.getAllPlannedMeals();
    }

    public static MealPlannerLocalDataSource getInstance(Context _context)
    {
        if(mealPlannerLocalDataSource == null)
        {
            mealPlannerLocalDataSource = new MealPlannerLocalDataSource(_context);
        }
        return mealPlannerLocalDataSource;
    }

    public void insertMealPlanned(MealPlannerDTO mealPlanner) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealPlannerDAO.insertMealPlan(mealPlanner);
            }
        }).start();
    }


    public void deleteMealPlanned(MealPlannerDTO mealPlannerDTO)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealPlannerDAO.deleteMealPlan(mealPlannerDTO);
            }
        }).start();
    }

    public LiveData<List<MealPlannerDTO>> getAllPlannedMeals() {
        return storedPlannedMeals;
    }

    public MealPlannerDTO getMealByDateAndType(String date, String mealType) {
        return mealPlannerDAO.getMealByDateAndType(date, mealType);
    }

}
