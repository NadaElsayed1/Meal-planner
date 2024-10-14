package com.example.mealsplanner.detailed_meals.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.mealsplanner.R;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.db.MealPlannerLocalDataSource;
import com.example.mealsplanner.detailed_meals.presenter.MealDetailsPresenter;
import com.example.mealsplanner.ingredients_show.IngredientAdapter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealPlannerDTO;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.List;

public class MealDetailsActivity extends AppCompatActivity implements SelectMealClickListener {

    private TextView mealName, mealDescription, mealCategory, mealCountry;
    private ImageView mealImage, addToPlanButton;
    private WebView mealVideo;
    private ImageView addToFavBtn;
    private Spinner mealTypeSpinner;
    private MealDetailsPresenter mealDetailsPresenter;
    private MealLocalDataSource repo;
    private MealPlannerLocalDataSource plannerRepo;
    private MealDTO currentMealDTO;
    private MealPlannerDTO currentMealPlannerDTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.darkToty));}

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
        mealName = findViewById(R.id.meal_name);
        mealImage = findViewById(R.id.meal_image);
        mealDescription = findViewById(R.id.item_meal_description);
        mealCategory = findViewById(R.id.item_meal_category);
        mealCountry = findViewById(R.id.item_meal_country);
        mealVideo = findViewById(R.id.item_meal_video);
        addToFavBtn = findViewById(R.id.add_to_fav_btn);
        mealTypeSpinner = findViewById(R.id.meal_type_spinner);
        addToPlanButton = findViewById(R.id.add_to_plan_button);
    }

    private void getMealDetailsFromIntent() {
        MealDTO mealDTOCountry = (MealDTO) getIntent().getSerializableExtra("MealDetails");
        MealDTO mealDTOCategory = (MealDTO) getIntent().getSerializableExtra("MealCategoryDetails");
        MealDTO mealDTORandom = (MealDTO) getIntent().getSerializableExtra("MealOftheDay");
        MealDTO mealDTOSearch = (MealDTO) getIntent().getSerializableExtra("MealSearch");
        MealPlannerDTO mealDTOPlanned = (MealPlannerDTO) getIntent().getSerializableExtra("plannedMeal");
        MealDTO favMealDTO = (MealDTO) getIntent().getSerializableExtra("favouriteMeal");

        mealDetailsPresenter = new MealDetailsPresenter(MealRemoteDataStructure.getInstance(), this);
        boolean isConnected = NetworkUtil.isConnected(this);

        if (mealDTOPlanned != null || favMealDTO != null) {
            if (mealDTOPlanned != null) {
                currentMealPlannerDTO = getMealFromPlanner(mealDTOPlanned.getIdMeal());
                addToPlanButton.setVisibility(View.GONE);
                mealTypeSpinner.setVisibility(View.GONE);
            } else if (favMealDTO != null) {
                currentMealDTO = getMealFromFavorites(favMealDTO.getIdMeal());
                addToFavBtn.setVisibility(View.GONE);
            }
            displayFavMealDetails(currentMealDTO);
            displayPlannedMealDetails(currentMealPlannerDTO);
        } else if (isConnected) {
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
            } else {
                finish();
            }
        } else {
            Toast.makeText(this, "No internet connection available. Please check your connection.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private MealDTO getMealFromFavorites(String mealId) {
        MealDTO mealDTO = repo.getAllMeals().getValue().stream()
                .filter(meal -> meal.getIdMeal().equals(mealId))
                .findFirst()
                .orElse(null);
        return mealDTO;
    }

    private MealPlannerDTO getMealFromPlanner(String mealId) {
        List<MealPlannerDTO> plannedMeals = plannerRepo.getAllPlannedMeals().getValue();
        for (MealPlannerDTO mealPlannerDTO : plannedMeals) {
            if (mealPlannerDTO.getIdMeal().equals(mealId)) {
                return mealPlannerDTO;
            }
        }
        return null;
    }


    private void displayFavMealDetails(MealDTO mealDTO) {
        if (mealDTO != null) {
            mealName.setText(mealDTO.getStrMeal());
            mealDescription.setText(mealDTO.getStrInstructions());
            mealCategory.setText(mealDTO.getStrCategory());
            mealCountry.setText(mealDTO.getStrArea());
            Glide.with(this)
                    .load(mealDTO.getStrMealThumb())
                    .transform(new RoundedCorners(16))
                    .into(mealImage);
            mealVideo.loadUrl("https://www.youtube.com/embed/" + mealDTO.getStrYoutube().split("=")[1]);
        }
    }
    private void displayPlannedMealDetails(MealPlannerDTO mealDTO){
            if (mealDTO != null) {
                mealName.setText(mealDTO.getStrMeal());
                mealDescription.setText(mealDTO.getStrInstructions());
                mealCategory.setText(mealDTO.getStrCategory());
                mealCountry.setText(mealDTO.getStrArea());
                Glide.with(this)
                        .load(mealDTO.getStrMealThumb())
                        .transform(new RoundedCorners(16))
                        .into(mealImage);
                mealVideo.loadUrl("https://www.youtube.com/embed/" + mealDTO.getStrYoutube().split("=")[1]);
            }
        }

    private void setupMealTypeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.meal_types, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
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
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            Calendar selectedDateCalendar = Calendar.getInstance();
            selectedDateCalendar.set(year, month, dayOfMonth);
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;

            if (selectedDateCalendar.before(calendar)) {
                Toast.makeText(MealDetailsActivity.this, "You cannot select a past date.", Toast.LENGTH_SHORT).show();
            } else {
                addMealToPlan(mealType, selectedDate);
            }
        }, currentYear, currentMonth, currentDay);

        datePickerDialog.show();
    }

//    private void addMealToPlan(String mealType, String selectedDate) {
//        if (currentMealDTO != null) {
//            try {
//                MealPlannerDTO existingMeal = plannerRepo.getMealByDateAndType(selectedDate, mealType);
//
//                if (existingMeal != null) {
//                    Toast.makeText(this, "A meal is already planned for " + mealType + " on " + selectedDate, Toast.LENGTH_SHORT).show();
//                } else {
//                    MealPlannerDTO mealPlanner = new MealPlannerDTO(currentMealDTO, selectedDate, mealType);
//                    mealPlanner.setIdMeal(currentMealDTO.getIdMeal());
//                    mealPlanner.setMealType(mealType);
//                    mealPlanner.setDate(selectedDate);
//                    plannerRepo.insertMealPlanned(mealPlanner);
//                    Toast.makeText(this, "Meal added to plan: " + currentMealDTO.getStrMeal(), Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e) {
//                Toast.makeText(this, "An error occurred while adding the meal to the plan.", Toast.LENGTH_SHORT).show();
//                Log.e("MealDetailsActivity", "Error adding meal to plan", e);
//            }
//        } else {
//            Toast.makeText(this, "Error: No meal selected!", Toast.LENGTH_SHORT).show();
//        }
//    }


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
                .transform(new RoundedCorners(16))
                .into(mealImage);

        RecyclerView ingredientsRecyclerView = findViewById(R.id.ingredients_recycler_view);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

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


    @Override
    public void OnSelect(MealDTO mealDTO) {
        try {
            repo.insert(mealDTO);
            Snackbar snackbar = Snackbar.make(
                    findViewById(android.R.id.content),
                    "Added to favorites: " + mealDTO.getStrMeal(),
                    Snackbar.LENGTH_LONG
            );
            snackbar.setAction("UNDO", v -> {
                repo.delete(mealDTO);
                Snackbar.make(findViewById(android.R.id.content), "Removed from favorites", Snackbar.LENGTH_SHORT).show();
            }).setActionTextColor(ContextCompat.getColor(this, R.color.backgroundTwo));
            snackbar.show();
        } catch (Exception e) {
            Snackbar.make(findViewById(android.R.id.content), "Error adding to favorites!", Snackbar.LENGTH_SHORT).show();
        }
    }

    public static class NetworkUtil {
        public static boolean isConnected(Context context) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
    }

}
