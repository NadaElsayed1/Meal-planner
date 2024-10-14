package com.example.mealsplanner.meals_countries.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.mealsplanner.R;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.meals_countries.presenter.MealCountriesPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;

import java.util.ArrayList;
import java.util.List;

public class MealCountriesFragment extends Fragment implements IMealCountriesFragment , OnCountryClickListener{
    private RecyclerView countriesRecyclerView;
    private TextView titleTextView;
    private MealCountriesAdapter mealCountriesAdapter;
    private MealCountriesPresenter mealCountriesPresenter;
    GridLayoutManager layoutManager;
    private MealRepository mealRepository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_countries, container, false);}

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTextView = view.findViewById(R.id.countries_title);
        countriesRecyclerView = view.findViewById(R.id.CountryListRecyclerView);
        layoutManager = new GridLayoutManager(this.getContext(),2);

        countriesRecyclerView.setLayoutManager(layoutManager);
        mealCountriesAdapter = new MealCountriesAdapter(getActivity(), new ArrayList<>(), meal -> onCountryClick(meal));
        countriesRecyclerView.setAdapter(mealCountriesAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(), MealLocalDataSource.getInstance(requireContext()));
        mealCountriesPresenter = new MealCountriesPresenter(mealRepository, this);
        mealCountriesPresenter.getMealCountries();
    }

    @Override
    public void showData(List<MealDTO> countriesList) {
        mealCountriesAdapter.setList(countriesList);
        mealCountriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String errorMessage) {
        new AlertDialog.Builder(requireContext())
                .setMessage("It seems that you're offline. Please turn on your Internet connection.")
                .setTitle("No Internet Connection")
                .setPositiveButton("Turn on Wi-Fi", (dialog, which) -> {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                })
                .setNegativeButton("Turn on Mobile Data", (dialog, which) -> {
                    startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                })
                .setNeutralButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                })
                .create()
                .show();
    }

    @Override
    public void onCountryClick(MealDTO meal) {
        Intent intent = new Intent(this.getActivity() , FilterCountryDetailsActivity.class);
        intent.putExtra("Meal",meal);
        startActivity(intent);

    }
}
