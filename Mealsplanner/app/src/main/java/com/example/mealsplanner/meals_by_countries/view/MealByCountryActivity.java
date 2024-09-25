package com.example.mealsplanner.meals_by_countries.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealsplanner.R;
import com.example.mealsplanner.meals_by_categories.presenter.MealByCategoryPresenter;
import com.example.mealsplanner.meals_by_categories.view.MealByCategoryActivity;
import com.example.mealsplanner.meals_by_categories.view.MealByCategoryAdapter;
import com.example.mealsplanner.meals_by_countries.presenter.MealByCountryPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealLocalDataSource;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

public class MealByCountryActivity extends AppCompatActivity implements IMealByCountryActivity {
    private static final String TAG = "ViewCountryMeal";
    private static final String TAG2 = "ErrorViewCountryMeal";
    MealLocalDataSource repo;
    RecyclerView Countries;
    RecyclerView.LayoutManager layoutManager;
    MealByCountryAdapter mealByCountryAdapter;
    MealRemoteDataStructure APIClient;
    MealByCountryPresenter mealByCountryPresenter;
    NetworkCallback nc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_by_country);

        /*Prepare Recycleview and show data using adapter*/
        Countries = findViewById(R.id.CountryRecyclerView);
        layoutManager = new LinearLayoutManager(MealByCountryActivity.this);
        Countries.setLayoutManager(layoutManager);
        mealByCountryAdapter = new MealByCountryAdapter(MealByCountryActivity.this,new ArrayList<>());
        Countries.setAdapter(mealByCountryAdapter);

        /*Network*/
        /*which needs callback*/
        APIClient = MealRemoteDataStructure.getInstance();

        /*repo instance for all app activities*/
        repo = MealLocalDataSource.getInstance(this.getApplicationContext());

        /*presenter creation by passing the Activity object (which implement the interface) to a reference of interface(Activity interface)*/
        mealByCountryPresenter = new MealByCountryPresenter(repo,this);
        nc = mealByCountryPresenter;  // Assign the presenter as the callback

        /*make network call*/
        APIClient.getMealsByCountry("Indian",nc);
    }

    @Override
    public void showData(List<MealDTO> show) {
        mealByCountryAdapter.setList(show);
        mealByCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(error).setTitle("An Error Occured");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}