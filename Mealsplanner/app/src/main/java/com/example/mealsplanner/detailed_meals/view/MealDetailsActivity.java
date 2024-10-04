package com.example.mealsplanner.detailed_meals.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
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

    private TextView mealName, mealDescription, mealCategory, mealCountry;
    private ImageView mealImage, addToPlanButton;
    private WebView mealVideo;
    private Button addToFavBtn;
    private Spinner mealTypeSpinner;
    private MealDetailsPresenter mealDetailsPresenter;
    private MealLocalDataSource repo;
    private MealPlannerLocalDataSource plannerRepo;
    private MealDTO currentMealDTO;
    private MealPlannerDTO currentMealPlannedDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        initUI();
        repo = MealLocalDataSource.getInstance(getApplicationContext());
        plannerRepo = MealPlannerLocalDataSource.getInstance(getApplicationContext());

        getMealDetailsFromIntent();

        addToFavBtn.setOnClickListener(v -> {
            if (currentMealDTO != null) {
                OnSelect(currentMealDTO);
            }
        });

        setupMealTypeSpinner();
        setupAddToPlanListener();
    }

    private void initUI() {
        mealName = findViewById(R.id.meal_name2);
        mealImage = findViewById(R.id.meal_image2);
        mealDescription = findViewById(R.id.item_meal_description2);
        mealCategory = findViewById(R.id.item_meal_category2);
        mealCountry = findViewById(R.id.item_meal_country2);
        mealVideo = findViewById(R.id.item_meal_video2);
        addToFavBtn = findViewById(R.id.add_to_fav_btn2);
        mealTypeSpinner = findViewById(R.id.meal_type_spinner);
        addToPlanButton = findViewById(R.id.add_to_plan_button);
    }

    private void getMealDetailsFromIntent() {
        MealDTO mealDTOCountry = (MealDTO) getIntent().getSerializableExtra("MealDetails");
        MealDTO mealDTOCategory = (MealDTO) getIntent().getSerializableExtra("MealCategoryDetails");
        MealDTO mealDTORandom = (MealDTO) getIntent().getSerializableExtra("MealOftheDay");
        MealDTO mealDTOSearch = (MealDTO) getIntent().getSerializableExtra("MealSearch");
        MealPlannerDTO mealDTOPlanned = (MealPlannerDTO) getIntent().getSerializableExtra("plannedMeal");

        mealDetailsPresenter = new MealDetailsPresenter(MealRemoteDataStructure.getInstance(), this);

        if (mealDTOCountry != null) {
            mealDetailsPresenter.lookupMealById(mealDTOCountry.getIdMeal());
            currentMealDTO = mealDTOCountry;
        } else if (mealDTOCategory != null) {
            mealDetailsPresenter.lookupMealById(mealDTOCategory.getIdMeal());
            currentMealDTO = mealDTOCategory;
        } else if (mealDTORandom != null) {
            mealDetailsPresenter.lookupMealById(mealDTORandom.getIdMeal());
            currentMealDTO = mealDTORandom;
        } else if (mealDTOSearch != null) {
            mealDetailsPresenter.lookupMealById(mealDTOSearch.getIdMeal());
            currentMealDTO = mealDTOSearch;
        } else if (mealDTOPlanned != null) {
            mealDetailsPresenter.lookupMealById(mealDTOPlanned.getIdMeal());
            // Make the calendar and spinner invisible
            addToPlanButton.setVisibility(View.GONE);
            mealTypeSpinner.setVisibility(View.GONE);
        } else {
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
        if (currentMealDTO != null) {
            MealPlannerDTO mealPlanner = new MealPlannerDTO(currentMealDTO, selectedDate, mealType);
            mealPlanner.setIdMeal(currentMealDTO.getIdMeal());
            mealPlanner.setMealType(mealType);
            mealPlanner.setDate(selectedDate);
            plannerRepo.insertMealPlanned(mealPlanner);
            Toast.makeText(this, "Meal added to plan: " + currentMealDTO.getStrMeal(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error: No meal selected!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateMealDetails(MealDTO mealDTO) {
        currentMealDTO = mealDTO;
        mealName.setText(mealDTO.getStrMeal());
        mealDescription.setText(mealDTO.getStrInstructions());
        mealCategory.setText(mealDTO.getStrCategory());
        mealCountry.setText(mealDTO.getStrArea());

        Glide.with(this)
                .load(mealDTO.getStrMealThumb())
                .into(mealImage);

        RecyclerView ingredientsRecyclerView = findViewById(R.id.ingredients_recycler_view);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        IngredientAdapter ingredientAdapter = new IngredientAdapter(this, mealDTO.getIngredients(), mealDTO.getMeasures());
        ingredientsRecyclerView.setAdapter(ingredientAdapter);

        String youtubeUrl = mealDTO.getStrYoutube();
        if (youtubeUrl != null && !youtubeUrl.isEmpty()) {
            String videoId = youtubeUrl.substring(youtubeUrl.lastIndexOf('=') + 1);
            String iframeHtml = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
            mealVideo.getSettings().setJavaScriptEnabled(true);
            mealVideo.loadData(iframeHtml, "text/html", "utf-8");
        }
    }

    public void updateMealDetails(MealPlannerDTO mealPlannerDTO) {
        currentMealPlannedDTO = mealPlannerDTO;
        mealName.setText(mealPlannerDTO.getStrMeal());
        mealDescription.setText(mealPlannerDTO.getStrInstructions());
        mealCategory.setText(mealPlannerDTO.getStrCategory());
        mealCountry.setText(mealPlannerDTO.getStrArea());

        Glide.with(this)
                .load(mealPlannerDTO.getStrMealThumb())
                .into(mealImage);

        RecyclerView ingredientsRecyclerView = findViewById(R.id.ingredients_recycler_view);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        IngredientAdapter ingredientAdapter = new IngredientAdapter(this, mealPlannerDTO.getIngredients(), mealPlannerDTO.getMeasures());
        ingredientsRecyclerView.setAdapter(ingredientAdapter);

        String youtubeUrl = mealPlannerDTO.getStrYoutube();
        if (youtubeUrl != null && !youtubeUrl.isEmpty()) {
            String videoId = youtubeUrl.substring(youtubeUrl.lastIndexOf('=') + 1);
            String iframeHtml = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
            mealVideo.getSettings().setJavaScriptEnabled(true);
            mealVideo.loadData(iframeHtml, "text/html", "utf-8");
        }
    }

    @Override
    public void OnSelect(MealDTO mealDTO) {
        try {
            repo.insert(mealDTO);
            Toast.makeText(this, "Added to favorites: " + mealDTO.getStrMeal(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error adding to favorites!", Toast.LENGTH_SHORT).show();
            Log.e("MealDetailsActivity", "Error adding to favorites", e);
        }
    }
}
