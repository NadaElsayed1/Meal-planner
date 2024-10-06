package com.example.mealsplanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.mealsplanner.R;
import com.example.mealsplanner.meal_of_the_day.view.MealOfTheDayFragment;
import com.example.mealsplanner.meals_categories.view.MealCategoriesFragment;
import com.example.mealsplanner.meals_countries.view.MealCountriesFragment;

public class CombinedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_combined, container, false);}

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getChildFragmentManager().beginTransaction()
                .replace(R.id.random_meal_container, new MealOfTheDayFragment())
                .commit();

        getChildFragmentManager().beginTransaction()
                .replace(R.id.categories_container, new MealCategoriesFragment())
                .commit();

        getChildFragmentManager().beginTransaction()
                .replace(R.id.countries_container, new MealCountriesFragment())
                .commit();
    }
}
