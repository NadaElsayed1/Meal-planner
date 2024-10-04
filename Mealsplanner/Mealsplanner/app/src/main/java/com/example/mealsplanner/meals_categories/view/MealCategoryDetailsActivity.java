package com.example.mealsplanner.meals_categories.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealsplanner.R;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.detailed_meals.view.MealDetailsActivity;
import com.example.mealsplanner.meals_by_categories.presenter.MealByCategoryPresenter;
import com.example.mealsplanner.meals_by_categories.view.IMealByCategoryView;
import com.example.mealsplanner.model.CategoryDTO;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import java.util.ArrayList;
import java.util.List;

public class MealCategoryDetailsActivity extends AppCompatActivity implements OnMealClickListener, IMealByCategoryView {

    private RecyclerView mealsRecyclerView2;
    private MealsAdapter mealsAdapter2;
    private MealByCategoryPresenter mealByCategoryPresenter;
    private MealRepository mealRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_category_details);

        Intent intent = getIntent();
        CategoryDTO categoryDTO = (CategoryDTO) intent.getSerializableExtra("MealCategory");

        Log.i("ReceivedCategoryData", "onCreate: " + categoryDTO.getStrCategory());

        mealsAdapter2 = new MealsAdapter(new ArrayList<>(),this,this);
        mealsRecyclerView2 = findViewById(R.id.meals_recycler_view2);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(), MealLocalDataSource.getInstance(this));

        mealsRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mealByCategoryPresenter = new MealByCategoryPresenter(mealRepository, this);
        mealByCategoryPresenter.getMealsByCategory(categoryDTO.getStrCategory());
        mealsRecyclerView2.setAdapter(mealsAdapter2);
    }



    @Override
    public void onMealClick(MealDTO meal) {
        Intent intent = new Intent(this, MealDetailsActivity.class);
        intent.putExtra("MealCategoryDetails",meal);
        Toast.makeText(this, "I am Leaving MealCategory  going to detailed", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Meal ID="+meal.getIdMeal(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    public void showData(List<MealDTO> show) {
        mealsAdapter2.setMealList(show);
        mealsAdapter2.notifyDataSetChanged();

    }

    @Override
    public void showErrMsg(String error) {
        Log.i("Error", "showErrMsg: ");
    }
}
