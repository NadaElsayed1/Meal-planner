package com.example.mealsplanner.meals_by_categories.view;

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
import com.example.mealsplanner.meal_of_the_day.presenter.MealOfTheDayPresenter;
import com.example.mealsplanner.meal_of_the_day.view.MealOfTheDayActivity;
import com.example.mealsplanner.meal_of_the_day.view.MealOfTheDayAdapter;
import com.example.mealsplanner.meals_by_categories.presenter.MealByCategoryPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealLocalDataSource;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

public class MealByCategoryActivity extends AppCompatActivity implements IMealByCategoryActivity {
    private static final String TAG = "ViewCategory";
    private static final String TAG2 = "ErrorViewCategory";
    MealLocalDataSource repo;
    RecyclerView Categories;
    RecyclerView.LayoutManager layoutManager;
    MealByCategoryAdapter mealByCategoryAdapter;
    MealRemoteDataStructure APIClient;
    MealByCategoryPresenter mealByCategoryPresenter;
    NetworkCallback nc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_by_category);
        /*Prepare Recycleview and show data using adapter*/
        Categories = findViewById(R.id.GategoryRecyclerView);
        layoutManager = new LinearLayoutManager(MealByCategoryActivity.this);
        Categories.setLayoutManager(layoutManager);
        mealByCategoryAdapter = new MealByCategoryAdapter(MealByCategoryActivity.this,new ArrayList<>());
        Categories.setAdapter(mealByCategoryAdapter);

        /*Network*/
        /*which needs callback*/
        APIClient = MealRemoteDataStructure.getInstance();

        /*repo instance for all app activities*/
        repo = MealLocalDataSource.getInstance(this.getApplicationContext());

        /*presenter creation by passing the Activity object (which implement the interface) to a reference of interface(Activity interface)*/
        mealByCategoryPresenter = new MealByCategoryPresenter(APIClient,this);
        nc = mealByCategoryPresenter;  // Assign the presenter as the callback

        /*make network call*/
        APIClient.getMealsByCategory("Beef",nc);
    }

    @Override
    public void showData(List<MealDTO> show) {
        mealByCategoryAdapter.setList(show);
        mealByCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(error).setTitle("An Error Occured");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}