package com.example.mealsplanner.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealPlannerDTO;

@Database(entities = {MealDTO.class, MealPlannerDTO.class}, version = 4, exportSchema = false)
public abstract class MealDatabase extends RoomDatabase {
    private static MealDatabase instance = null;

    public abstract MealDAO getMealDao();
    public abstract MealPlannerDAO getMealPlannerDao();

    public static synchronized MealDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MealDatabase.class, "meal_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
//    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE MealPlannerDTO ADD COLUMN newColumnName TEXT");
//        }
//    };

}
