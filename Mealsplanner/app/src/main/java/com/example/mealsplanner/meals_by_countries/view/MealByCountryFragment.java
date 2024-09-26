package com.example.mealsplanner.meals_by_countries.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.mealsplanner.R;
import com.example.mealsplanner.meals_by_countries.presenter.MealByCountryPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

public class MealByCountryFragment extends Fragment implements IMealByCountryView {
    private static final String TAG = "ViewCountryMeal";
    private static final String TAG2 = "ErrorViewCountryMeal";

    MealLocalDataSource repo;
    RecyclerView Countries;
    TextView Title;
    RecyclerView.LayoutManager layoutManager;
    MealByCountryAdapter mealByCountryAdapter;
    MealRemoteDataStructure APIClient;
    MealByCountryPresenter mealByCountryPresenter;
    NetworkCallback nc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_by_country, container, false);

        // Prepare RecyclerView and show data using adapter
        Title = view.findViewById(R.id.meals_by_country_title);
        Countries = view.findViewById(R.id.CountryRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        Countries.setLayoutManager(layoutManager);
        mealByCountryAdapter = new MealByCountryAdapter(getContext(), new ArrayList<>());
        Countries.setAdapter(mealByCountryAdapter);

        // Network setup
        APIClient = MealRemoteDataStructure.getInstance();

        // Repo instance for all app activities
        repo = MealLocalDataSource.getInstance(getContext().getApplicationContext());

        // Presenter creation by passing the fragment object (which implements the interface)
        mealByCountryPresenter = new MealByCountryPresenter(APIClient, this);
        nc = mealByCountryPresenter;  // Assign the presenter as the callback

        // Make network call
        APIClient.getMealsByCountry("Indian", nc);

        return view;
    }

    @Override
    public void showData(List<MealDTO> show) {
        mealByCountryAdapter.setList(show);
        mealByCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
