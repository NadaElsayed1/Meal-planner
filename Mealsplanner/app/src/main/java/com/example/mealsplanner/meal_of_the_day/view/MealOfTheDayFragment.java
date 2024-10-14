package com.example.mealsplanner.meal_of_the_day.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import com.example.mealsplanner.R;
import com.example.mealsplanner.detailed_meals.view.MealDetailsActivity;
import com.example.mealsplanner.meal_of_the_day.presenter.MealOfTheDayPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import java.util.ArrayList;
import java.util.List;

public class MealOfTheDayFragment extends Fragment implements IMealOfTheDayView, OnMealClickListener {
    private static final String TAG = "ViewData";
    private static final String TAG2 = "ErrorViewData";
    private MealRepository mealRepository;
    private RecyclerView suggestMeal;
    private TextView Title;
    private RecyclerView.LayoutManager layoutManager;
    private MealOfTheDayAdapter mealOfTheDayAdapter;
    private MealOfTheDayPresenter mealOfTheDayPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_meal_of_the_day, container, false);}

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Title = view.findViewById(R.id.meal_of_the_day_title);
        suggestMeal = view.findViewById(R.id.RandomRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        suggestMeal.setLayoutManager(layoutManager);
        mealOfTheDayAdapter = new MealOfTheDayAdapter(getContext(), new ArrayList<>(), meal -> onMealClick(meal));
        suggestMeal.setAdapter(mealOfTheDayAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(),MealLocalDataSource.getInstance(requireContext()));

        mealOfTheDayPresenter = new MealOfTheDayPresenter(mealRepository, this);
        mealOfTheDayPresenter.getMealOfTheDay();
    }

    @Override
    public void showData(List<MealDTO> meals) {
        mealOfTheDayAdapter.setList(meals);
        mealOfTheDayAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        new AlertDialog.Builder(requireContext())
                .setMessage("It seems that you're offline. Please turn on your Internet connection.")
                .setTitle("No Internet Connection")
                .setPositiveButton("Turn on Wi-Fi", (dialog, which) -> {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                })
                .setNegativeButton("Turn on Mobile Data", (dialog, which) -> {
                    startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                })
                .setNeutralButton("Cancel", (dialog, which) -> {
                    dialog.dismiss();
                })
                .create()
                .show();
    }

     @Override
     public void onMealClick(MealDTO meal) {
         Intent intent = new Intent(this.getActivity() , MealDetailsActivity.class);
         intent.putExtra("MealOftheDay",meal);
         startActivity(intent);
    }
}
