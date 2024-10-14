package com.example.mealsplanner.meal_ingredients.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
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
    return inflater.inflate(R.layout.fragment_meal_ingredient, container, false);}

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTextView = view.findViewById(R.id.ingredients_title);
        IngredientsRecyclerView = view.findViewById(R.id.IngredientListRecyclerView);
        IngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mealIngredientAdapter = new MealIngredientAdapter(getActivity(), new ArrayList<>());
        IngredientsRecyclerView.setAdapter(mealIngredientAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(), MealLocalDataSource.getInstance(requireContext()));

        mealIngredientPresenter = new MealIngredientPresenter(mealRepository, this);
        mealIngredientPresenter.getMealIngredients();
    }

    @Override
    public void showData(List<MealDTO> countriesList) {
        mealIngredientAdapter.setList(countriesList);
        mealIngredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String errorMessage) {
        if (getActivity() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("It seems that you're offline. Please turn on your Internet connection.")
                    .setTitle("No Internet Connection")
                    .setPositiveButton("Turn on Wi-Fi", (dialog, which) -> {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    })
                    .setNegativeButton("Turn on Mobile Data", (dialog, which) -> {
                        startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                    })
                    .setNeutralButton("Cancel", (dialog, which) -> {
                        dialog.dismiss();
                    });
            AlertDialog dialog =  builder.create();
                    dialog.show();
        }
    }
}
