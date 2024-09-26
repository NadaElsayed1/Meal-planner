package com.example.mealsplanner.meal_of_the_day.view;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.example.mealsplanner.R;
import com.example.mealsplanner.meal_of_the_day.presenter.MealOfTheDayPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import java.util.ArrayList;
import java.util.List;

public class MealOfTheDayFragment extends Fragment implements IMealOfTheDayView {
    private static final String TAG = "ViewData";
    private static final String TAG2 = "ErrorViewData";
    private MealLocalDataSource repo;
    private RecyclerView suggestMeal;
    private TextView Title;
    private RecyclerView.LayoutManager layoutManager;
    private MealOfTheDayAdapter mealOfTheDayAdapter;
    private MealRemoteDataStructure APIClient;
    private MealOfTheDayPresenter mealOfTheDayPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_of_the_day, container, false);

        /*Prepare RecyclerView and show data using adapter*/
        Title = view.findViewById(R.id.meal_of_the_day_title);
        suggestMeal = view.findViewById(R.id.RandomRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        suggestMeal.setLayoutManager(layoutManager);
        mealOfTheDayAdapter = new MealOfTheDayAdapter(getContext(), new ArrayList<>());
        suggestMeal.setAdapter(mealOfTheDayAdapter);

        /*Network*/
        APIClient = MealRemoteDataStructure.getInstance();

        /*repo instance for all app activities*/
        repo = MealLocalDataSource.getInstance(requireContext());

        /*Presenter creation by passing the Fragment object (which implements the interface)*/
        mealOfTheDayPresenter = new MealOfTheDayPresenter(APIClient, this);
        mealOfTheDayPresenter.getMealOfTheDay();

        return view;
    }

    @Override
    public void showData(List<MealDTO> meals) {
        mealOfTheDayAdapter.setList(meals);
        mealOfTheDayAdapter.notifyDataSetChanged();
        if (meals != null && !meals.isEmpty()) {
            Log.d(TAG, "showData: Successfully updated with " + meals.size() + " meals.");
        } else {
            Log.d(TAG, "showData: No meals to display.");
        }
    }

    @Override
    public void showErrMsg(String error) {
        new AlertDialog.Builder(requireContext())
                .setMessage(error)
                .setTitle("An Error Occurred")
                .create()
                .show();
        Log.e(TAG2, "showErrMsg: " + error);
    }
}
