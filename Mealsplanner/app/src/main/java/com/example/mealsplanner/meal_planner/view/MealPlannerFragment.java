package com.example.mealsplanner.meal_planner.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealsplanner.R;
import com.example.mealsplanner.db.MealPlannerLocalDataSource;
import com.example.mealsplanner.detailed_meals.view.MealDetailsActivity;
import com.example.mealsplanner.meal_planner.presenter.MealPlannerPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealPlannerDTO;
import com.example.mealsplanner.model.MealRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MealPlannerFragment extends Fragment implements IMealPlannerView, OnMealClickListener {
    private static final String TAG = "MealPlannerFragment";
    private static final String TAG_ERROR = "MealPlannerFragmentError";

    private MealPlannerLocalDataSource mealPlannerLocalDataSource;
    private RecyclerView mealPlannerRecyclerView;
    private MealPlannerAdapter adapter;
    private MealPlannerPresenter plannerPresenter;
    private LiveData<List<MealPlannerDTO>> receivedMeals;
    private TextView titleTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_planner, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents(view);
        setupRecyclerView();
        setupObserver();
    }

    private void initializeComponents(View view) {
        mealPlannerLocalDataSource = MealPlannerLocalDataSource.getInstance(getContext());
        titleTextView = view.findViewById(R.id.mealPlannerTitle);
        mealPlannerRecyclerView = view.findViewById(R.id.mealPlannerRecyclerView);
        plannerPresenter = new MealPlannerPresenter(this, mealPlannerLocalDataSource);
    }

    private void setupRecyclerView() {
        mealPlannerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MealPlannerAdapter(getContext(), new ArrayList<>(), new DeletPlannedMeal() {
            @Override
            public void OnDelet(MealPlannerDTO mealPlannerDTO) {
                plannerPresenter.removeMealFromPlanner(mealPlannerDTO);
            }
        },this::onMealClick);
        mealPlannerRecyclerView.setAdapter(adapter);
    }

    private void setupObserver() {
        receivedMeals = mealPlannerLocalDataSource.getAllPlannedMeals();
        receivedMeals.observe(getViewLifecycleOwner(), new Observer<List<MealPlannerDTO>>() {
            @Override
            public void onChanged(List<MealPlannerDTO> mealPlannerDTOS) {
                adapter.updateMealPlannerList(mealPlannerDTOS);
            }
        });
    }

    @Override
    public void showMealPlans(List<MealPlannerDTO> mealPlannerList) {
        adapter.updateMealPlannerList(mealPlannerList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMealClick(MealPlannerDTO meal) {
        Intent intent = new Intent(getContext(), MealDetailsActivity.class);
        intent.putExtra("plannedMeal", meal);
        startActivity(intent);
    }

    @Override
    public void RemovePlannedItem(MealPlannerDTO mealPlannerDTO) {
        int position = adapter.getPlannedMeals().indexOf(mealPlannerDTO);
        adapter.removeItem(position);
        Snackbar snackbar = Snackbar.make(
                mealPlannerRecyclerView,
                "Deleted: " + mealPlannerDTO.getStrMeal(),
                Snackbar.LENGTH_LONG
        );
        snackbar.setAction("UNDO", v -> {
            adapter.restoreItem(mealPlannerDTO, position);
            mealPlannerRecyclerView.scrollToPosition(position);
        });
        snackbar.setActionTextColor(ContextCompat.getColor(getContext(), R.color.backgroundTwo)); // Set custom color
        snackbar.show();
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                    plannerPresenter.repo.deleteMealPlanned(mealPlannerDTO);
                }
            }
        });
    }

}
