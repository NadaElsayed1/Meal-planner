package com.example.mealsplanner;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.darkToty));}


            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setItemIconTintList(ContextCompat.getColorStateList(this, R.color.backgroundColor));

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
