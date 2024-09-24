package com.example.mealsplanner.all_meals.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.mealsplanner.R;
import com.example.mealsplanner.all_meals.presenter.MealPresenter;
import com.example.mealsplanner.db.MealDatabase;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealLocalDataSource;
import com.example.mealsplanner.network.MealService;
import com.example.mealsplanner.network.RetrofitClient; // Import the Retrofit client
import com.google.android.material.snackbar.Snackbar;

public class MealOfTheDayActivity extends AppCompatActivity implements IMealOfTheDayActivity {

    private MealPresenter presenter;
    private TextView mealNameText;
    private ImageView mealImageView;
    private Button addToFavButton;
    private MealDTO currentMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_of_the_day);

        mealNameText = findViewById(R.id.mealName);
        mealImageView = findViewById(R.id.mealImage);
        addToFavButton = findViewById(R.id.addToFavButton);

        // Initialize presenter with repository
        MealLocalDataSource repository = new MealLocalDataSource(
                RetrofitClient.getRetrofitInstance().create(MealService.class),
                MealDatabase.getInstance(this).mealDao() // Assuming you have a getInstance() method for MealDatabase
        );
        presenter = new MealPresenter(this, repository);

        // Fetch meal of the day
        presenter.fetchMealOfTheDay();

        addToFavButton.setOnClickListener(v -> {
            presenter.addMealToFavorites(currentMeal);
        });
    }
    @Override
    public void displayMealOfTheDay(MealDTO meal) {
        currentMeal = meal;
        mealNameText.setText(meal.getStrMeal());
        Glide.with(this).load(meal.getStrMealThumb()).into(mealImageView);
        mealImageView.setOnClickListener(v -> onMealClick(meal));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Snackbar.make(findViewById(R.id.rootLayout), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onMealClick(MealDTO meal) {
        Toast.makeText(this, "Clicked on: " + meal.getStrMeal(), Toast.LENGTH_SHORT).show();
    }
}
