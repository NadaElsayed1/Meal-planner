package com.example.mealsplanner.meal_ingredients.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mealsplanner.R;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.meal_ingredients.presenter.MealIngredientPresenter;
import com.example.mealsplanner.meals_countries.presenter.MealCountriesPresenter;
import com.example.mealsplanner.meals_countries.view.MealCountriesAdapter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;

import java.util.ArrayList;
import java.util.List;

public class MealIngredientFragment extends Fragment implements IMealIngredientFragment {
    private RecyclerView IngredientsRecyclerView;
    private TextView titleTextView;
    private MealIngredientAdapter mealIngredientAdapter;
    private MealIngredientPresenter mealIngredientPresenter;
    private MealRepository mealRepository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_ingredient, container, false);

        // Prepare UI elements
        titleTextView = view.findViewById(R.id.ingredients_title);
        IngredientsRecyclerView = view.findViewById(R.id.IngredientListRecyclerView);
        IngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Set adapter
        mealIngredientAdapter = new MealIngredientAdapter(getActivity(), new ArrayList<>());
        IngredientsRecyclerView.setAdapter(mealIngredientAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(), MealLocalDataSource.getInstance(requireContext()));

        // Presenter and network call
        mealIngredientPresenter = new MealIngredientPresenter(mealRepository, this);
        mealIngredientPresenter.getMealIngredients();

        return view;
    }

    @Override
    public void showData(List<MealDTO> countriesList) {
        if (countriesList != null && !countriesList.isEmpty())
        {
            mealIngredientAdapter.setList(countriesList);
            mealIngredientAdapter.notifyDataSetChanged();
            for (MealDTO meal : countriesList) {
                Log.d("Showing", "Ingredient: " + meal.getStrIngredient());  // Assuming getStrIngredient() method exists
            }
        } else {
            // Handle the case where the list is empty or null
            Log.d("Showing", "No ingredients found or list is null");
        }
    }

    @Override
    public void showErrMsg(String errorMessage) {
        if (getActivity() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(errorMessage).setTitle("An Error Occurred");
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
