package com.example.mealsplanner;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.mealsplanner.favourite.view.FavouriteMealsFragment;
import com.example.mealsplanner.meal_planner.view.MealPlannerFragment;
import com.example.mealsplanner.meals_search.view.MealSearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        if (savedInstanceState == null) {
            loadFragment(new CombinedFragment());
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    selectedFragment = new CombinedFragment();
                } else if (itemId == R.id.search) {
                    selectedFragment = new MealSearchFragment();
                } else if (itemId == R.id.favourite) {
                    selectedFragment = new FavouriteMealsFragment();
                }
                else if (itemId == R.id.planner) {
                    selectedFragment = new MealPlannerFragment();
                }

                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }

                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}

//package com.example.mealsplanner;
//
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.example.mealsplanner.lookup_meal_by_id.view.MealByIDFragment;
//import com.example.mealsplanner.meal_ingredients.view.MealIngredientFragment;
//import com.example.mealsplanner.meal_of_the_day.view.MealOfTheDayFragment;
//import com.example.mealsplanner.meals_by_ingredient.view.MealByIngredientFragment;
//import com.example.mealsplanner.meals_categories.view.MealCategoriesFragment;
//import com.example.mealsplanner.meals_by_categories.view.MealByCategoryFragment;
//import com.example.mealsplanner.meals_by_countries.view.MealByCountryFragment;
//import com.example.mealsplanner.meals_countries.view.MealCountriesFragment;
//import com.example.mealsplanner.meals_search.view.MealSearchFragment;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(ContextCompat.getColor(this, R.color.lightGreen));
//
//        // Load default fragment when activity starts (e.g., MealOfTheDayFragment)
//        if (savedInstanceState == null) {
//            loadFragment(new MealOfTheDayFragment());
//        }
//
//        // Setup the CardView click listeners
//        CardView mealOfTheDayCard = findViewById(R.id.meal_of_the_day_card);
//        mealOfTheDayCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment(new MealOfTheDayFragment());
//            }
//        });
//
//        CardView mealsByCategoryCard = findViewById(R.id.meals_by_category_card);
//        mealsByCategoryCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment(new MealByCategoryFragment());
//            }
//        });
//
//        CardView mealsByCountryCard = findViewById(R.id.meals_by_country_card);
//        mealsByCountryCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment(new MealByCountryFragment());
//            }
//        });
//
//        CardView searchMealsCard = findViewById(R.id.search_meals_card);
//        searchMealsCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment(new MealSearchFragment());
//            }
//        });
//
//        // Add click listener for Meal Categories
//        CardView mealCategoriesCard = findViewById(R.id.meals_categories_card);
//        mealCategoriesCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment(new MealCategoriesFragment());
//            }
//        });
//
//        // Add click listener for Meal countries list
//        CardView countrylistCard = findViewById(R.id.country_list_card);
//        countrylistCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadFragment(new MealCountriesFragment());
//            }
//        });
//
//
//    // Add click listener for Meal ingredients list
//    CardView ingredientlistCard = findViewById(R.id.ingredient_list_card);
//        ingredientlistCard.setOnClickListener(new View.OnClickListener()
//
//    {
//        @Override
//        public void onClick (View v){
//        loadFragment(new MealIngredientFragment());
//    }
//    });
//
//        // Add click listener for Meal ingredients
//        CardView ingredientCard = findViewById(R.id.ingredient_card);
//        ingredientCard.setOnClickListener(new View.OnClickListener()
//
//        {
//            @Override
//            public void onClick (View v){
//                loadFragment(new MealByIngredientFragment());
//            }
//        });
//
//        // Add click listener for Meal ID
//        CardView mealId_card = findViewById(R.id.mealId_card);
//        mealId_card.setOnClickListener(new View.OnClickListener()
//
//        {
//            @Override
//            public void onClick (View v){
//                loadFragment(new MealByIDFragment());
//            }
//        });
//}
//
//    // Helper method to load fragments into the container
//    private void loadFragment(Fragment fragment) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container, fragment);  // Use the fragment container from XML
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
//}