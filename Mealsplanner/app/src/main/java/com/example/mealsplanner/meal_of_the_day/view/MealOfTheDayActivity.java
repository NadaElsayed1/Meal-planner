package com.example.mealsplanner.meal_of_the_day.view;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealsplanner.R;
import com.example.mealsplanner.meal_of_the_day.presenter.MealOfTheDayPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealLocalDataSource;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

public class MealOfTheDayActivity extends AppCompatActivity implements IMealOfTheDayActivity {
    private static final String TAG = "ViewData";
    private static final String TAG2 = "ErrorViewData";
    MealLocalDataSource repo;
    RecyclerView SuggestMeal;
    RecyclerView.LayoutManager layoutManager;
    MealOfTheDayAdapter mealOfTheDayAdapter;
    MealRemoteDataStructure APIClient;
    MealOfTheDayPresenter mealOfTheDayPresenter;
    NetworkCallback nc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_of_the_day);

        /*Prepare Recycleview and show data using adapter*/
        SuggestMeal = findViewById(R.id.RandomRecyclerView);
        layoutManager = new LinearLayoutManager(MealOfTheDayActivity.this);
        SuggestMeal.setLayoutManager(layoutManager);
        mealOfTheDayAdapter = new MealOfTheDayAdapter(MealOfTheDayActivity.this,new ArrayList<>());
        SuggestMeal.setAdapter(mealOfTheDayAdapter);

        /*Network*/
        /*which needs callback*/
        APIClient = MealRemoteDataStructure.getInstance();

        /*repo instance for all app activities*/
        repo = MealLocalDataSource.getInstance(this.getApplicationContext());

        /*presenter creation by passing the Activity object (which implement the interface) to a reference of interface(Activity interface)*/
        mealOfTheDayPresenter = new MealOfTheDayPresenter(repo,this);
//        allProductpresenter.presenter();  // Correctly pass the view and repo
        nc = mealOfTheDayPresenter;  // Assign the presenter as the callback

        /*make network call*/
        APIClient.getMealOfTheDay(nc);
    }

    @Override
    public void showData(List<MealDTO> show) {
        mealOfTheDayAdapter.setList(show);
        mealOfTheDayAdapter.notifyDataSetChanged();
        if (show != null && !show.isEmpty()) {
            Log.d(TAG, "showData: Successfully updated with " + show.size() + " meals.");
            for (MealDTO meal : show) {
                Log.d(TAG, "showData: Meal - " + meal.getStrMeal());
            }
        } else {
            Log.d(TAG, "showData: No meals to display.");
        }
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(error).setTitle("An Error Occured");
        AlertDialog dialog = builder.create();
        dialog.show();
        Log.e(TAG2, "showErrMsg: " + error);
    }
}