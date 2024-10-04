package com.example.mealsplanner.meals_countries.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
        View view = inflater.inflate(R.layout.fragment_meal_countries, container, false);

        titleTextView = view.findViewById(R.id.countries_title);
        countriesRecyclerView = view.findViewById(R.id.CountryListRecyclerView);
        layoutManager = new GridLayoutManager(this.getContext(),2);

        countriesRecyclerView.setLayoutManager(layoutManager);
        mealCountriesAdapter = new MealCountriesAdapter(getActivity(), new ArrayList<>(), meal -> onCountryClick(meal));
        countriesRecyclerView.setAdapter(mealCountriesAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(), MealLocalDataSource.getInstance(requireContext()));
        mealCountriesPresenter = new MealCountriesPresenter(mealRepository, this);
        mealCountriesPresenter.getMealCountries();

        return view;
    }

    @Override
    public void showData(List<MealDTO> countriesList) {
        mealCountriesAdapter.setList(countriesList);
        mealCountriesAdapter.notifyDataSetChanged();
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

    @Override
    public void onCountryClick(MealDTO meal) {
        Intent intent = new Intent(this.getActivity() , MealCountryDetailsActivity.class);
        intent.putExtra("Meal",meal);
        startActivity(intent);

    }
}
