package com.example.mealsplanner;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mealsplanner.meal_of_the_day.view.MealOfTheDayFragment;
import com.example.mealsplanner.meals_by_categories.view.MealByCategoryFragment;
import com.example.mealsplanner.meals_by_countries.view.MealByCountryFragment;
import com.example.mealsplanner.meals_search.view.MealSearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.lightGreen));

        // Load default fragment when activity starts (e.g., MealOfTheDayFragment)
        if (savedInstanceState == null) {
            loadFragment(new MealOfTheDayFragment());
        }

        // Setup the CardView click listeners
        CardView mealOfTheDayCard = findViewById(R.id.meal_of_the_day_card);
        mealOfTheDayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new MealOfTheDayFragment());
            }
        });

        CardView mealsByCategoryCard = findViewById(R.id.meals_by_category_card);
        mealsByCategoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new MealByCategoryFragment());
            }
        });

        CardView mealsByCountryCard = findViewById(R.id.meals_by_country_card);
        mealsByCountryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new MealByCountryFragment());
            }
        });

        CardView searchMealsCard = findViewById(R.id.search_meals_card);
        searchMealsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new MealSearchFragment());
            }
        });
    }

    // Helper method to load fragments into the container
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);  // Use the fragment container from XML
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
