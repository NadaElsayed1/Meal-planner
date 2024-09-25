package com.example.mealsplanner;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealsplanner.meal_of_the_day.view.MealOfTheDayActivity;
import com.example.mealsplanner.meals_by_categories.view.MealByCategoryActivity;
import com.example.mealsplanner.meals_by_countries.view.MealByCountryActivity;
import com.example.mealsplanner.meals_search.view.MealSearchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, MealOfTheDayActivity.class);
        startActivity(intent);
    }
}