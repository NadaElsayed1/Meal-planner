package com.example.mealsplanner.meals_countries.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealsplanner.R;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.detailed_meals.view.MealDetailsActivity;
import com.example.mealsplanner.meals_by_countries.presenter.MealByCountryPresenter;
import com.example.mealsplanner.meals_by_countries.view.IMealByCountryView;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import java.util.ArrayList;
import java.util.List;

public class MealCountryDetailsActivity extends AppCompatActivity implements OnMealClickListener, IMealByCountryView {
    private RecyclerView mealsRecyclerView;
    private MealsAdapter mealsAdapter;
    private MealByCountryPresenter mealByCountryPresenter;
    private MealRepository mealRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_country_details);

        Intent intent = getIntent();
        MealDTO mealDTO = (MealDTO) intent.getSerializableExtra("Meal");
        Log.i("ReceivedData", "onCreate: " + mealDTO.getStrMeal());

        mealsAdapter = new MealsAdapter(new ArrayList<>(),this,this);

//        country_name = findViewById(R.id.country_name);
//        country_image = findViewById(R.id.country_image);
        mealsRecyclerView = findViewById(R.id.meals_recycler_view);
        mealsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mealsRecyclerView.setAdapter(mealsAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(), MealLocalDataSource.getInstance(this));

        mealByCountryPresenter = new MealByCountryPresenter(mealRepository, this);
        mealByCountryPresenter.getMealsByCountry(mealDTO.getStrArea());
    }

    @Override
    public void onMealClick(MealDTO meal) {
        Intent intent = new Intent(this, MealDetailsActivity.class);
        intent.putExtra("MealDetails",meal);
        startActivity(intent);
    }

    @Override
    public void showData(List<MealDTO> show) {
        mealsAdapter.setMealList(show);
        mealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        Log.i("Error", "showErrMsg: ");
    }
}
