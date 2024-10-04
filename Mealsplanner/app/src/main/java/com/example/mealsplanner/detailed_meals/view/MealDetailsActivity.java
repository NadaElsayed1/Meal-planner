package com.example.mealsplanner.detailed_meals.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealsplanner.R;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.db.MealPlannerLocalDataSource;
import com.example.mealsplanner.detailed_meals.presenter.MealDetailsPresenter;
import com.example.mealsplanner.ingredients_show.IngredientAdapter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealPlannerDTO;
import com.example.mealsplanner.network.MealRemoteDataStructure;

import java.util.Calendar;

public class MealDetailsActivity extends AppCompatActivity implements SelectMealClickListener {

    private TextView mealName2, mealDescription2, mealCategory2, mealCountry2;
    private ImageView mealImage2, addToPlanButton;
    private WebView mealVideo2;
    private Button addToFavBtn2;
    private Spinner mealTypeSpinner;
    private MealDetailsPresenter mealDetailsPresenter2;
    private MealLocalDataSource repo2;
    private MealPlannerLocalDataSource plannerRepo;
    private MealDTO currentMealDTO2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details2);

        initUI();
        repo2 = MealLocalDataSource.getInstance(getApplicationContext());
        plannerRepo = MealPlannerLocalDataSource.getInstance(getApplicationContext());

        getMealDetailsFromIntent();

        addToFavBtn2.setOnClickListener(v -> {
            if (currentMealDTO2 != null) {
                OnSelect(currentMealDTO2);
            }
        });

        setupMealTypeSpinner();
        setupAddToPlanListener();
    }

    private void initUI() {
        mealName2 = findViewById(R.id.meal_name2);
        mealImage2 = findViewById(R.id.meal_image2);
        mealDescription2 = findViewById(R.id.item_meal_description2);
        mealCategory2 = findViewById(R.id.item_meal_category2);
        mealCountry2 = findViewById(R.id.item_meal_country2);
        mealVideo2 = findViewById(R.id.item_meal_video2);
        addToFavBtn2 = findViewById(R.id.add_to_fav_btn2);
        mealTypeSpinner = findViewById(R.id.meal_type_spinner);
        addToPlanButton = findViewById(R.id.add_to_plan_button);
    }

    private void getMealDetailsFromIntent() {
        MealDTO mealDTOCountry = (MealDTO) getIntent().getSerializableExtra("MealDetails");
        MealDTO mealDTOCategory = (MealDTO) getIntent().getSerializableExtra("MealCategoryDetails");
        MealDTO mealDTORandom = (MealDTO) getIntent().getSerializableExtra("MealOftheDay");


        mealDetailsPresenter2 = new MealDetailsPresenter(MealRemoteDataStructure.getInstance(), this);

        if (mealDTOCountry != null) {
            mealDetailsPresenter2.lookupMealById(mealDTOCountry.getIdMeal());
            currentMealDTO2 = mealDTOCountry;
        } else if (mealDTOCategory != null) {
            mealDetailsPresenter2.lookupMealById(mealDTOCategory.getIdMeal());
            currentMealDTO2 = mealDTOCategory;
        } else if (mealDTORandom != null)
        {
            mealDetailsPresenter2.lookupMealById(mealDTORandom.getIdMeal());
            currentMealDTO2 = mealDTORandom;
        }
        else {
            Log.e("MealDetailsActivity", "No MealDTO found in the intent");
            finish();
        }
    }

    private void setupMealTypeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.meal_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealTypeSpinner.setAdapter(adapter);
    }

    private void setupAddToPlanListener() {
        addToPlanButton.setOnClickListener(v -> {
            String selectedMealType = mealTypeSpinner.getSelectedItem().toString();
            showDatePickerDialog(selectedMealType);
        });
    }

    private void showDatePickerDialog(String mealType) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            addMealToPlan(mealType, selectedDate);
        }, year, month, day);
        datePickerDialog.show();
    }

    private void addMealToPlan(String mealType, String selectedDate) {
        if (currentMealDTO2 != null) {
            MealPlannerDTO mealPlanner = new MealPlannerDTO(currentMealDTO2.getStrMeal(), mealType, selectedDate, currentMealDTO2.getStrMealThumb());
            plannerRepo.insertMealPlanned(mealPlanner);
            Toast.makeText(this, "Meal added to plan: " + currentMealDTO2.getStrMeal(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error: No meal selected!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateMealDetails(MealDTO mealDTO) {
        currentMealDTO2 = mealDTO;
        mealName2.setText(mealDTO.getStrMeal());
        mealDescription2.setText(mealDTO.getStrInstructions());
        mealCategory2.setText(mealDTO.getStrCategory());
        mealCountry2.setText(mealDTO.getStrArea());

        Glide.with(this)
                .load(mealDTO.getStrMealThumb())
                .into(mealImage2);

        // Set up RecyclerView for ingredients
        RecyclerView ingredientsRecyclerView = findViewById(R.id.ingredients_recycler_view);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        IngredientAdapter ingredientAdapter = new IngredientAdapter(this, mealDTO.getIngredients(), mealDTO.getMeasures());
        ingredientsRecyclerView.setAdapter(ingredientAdapter);

        // Load the YouTube video if available
        String youtubeUrl = mealDTO.getStrYoutube();
        if (youtubeUrl != null && !youtubeUrl.isEmpty()) {
            String videoId = youtubeUrl.substring(youtubeUrl.lastIndexOf('=') + 1);
            String iframeHtml = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
            mealVideo2.getSettings().setJavaScriptEnabled(true);
            mealVideo2.loadData(iframeHtml, "text/html", "utf-8");
        }
    }

    @Override
    public void OnSelect(MealDTO mealDTO) {
        try {
            repo2.insert(mealDTO);
            Toast.makeText(this, "Added to favorites: " + mealDTO.getStrMeal(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("MealDetailsActivity", "Error adding to favorites", e);
            Toast.makeText(this, "Failed to add to favorites", Toast.LENGTH_SHORT).show();
        }
    }
}
