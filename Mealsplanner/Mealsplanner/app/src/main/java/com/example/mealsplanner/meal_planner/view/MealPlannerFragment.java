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
    private static final String TAG = "ViewPlane";
    private static final String TAG2 = "ErrorViewPlane";
    private MealPlannerLocalDataSource mealPlannerLocalDataSource;
    private RecyclerView mealPlannerRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MealPlannerAdapter adapter;
    private MealPlannerPresenter plannerpresenter;
    LiveData<List<MealPlannerDTO>> recieved;
    private String selectedDate;
    private TextView Title;

//    private Spinner mealTypeSpinner;
//    private Button selectDateButton, addMealButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_planner, container, false);}

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mealPlannerLocalDataSource == null) {
            mealPlannerLocalDataSource = MealPlannerLocalDataSource.getInstance(getContext());
        }
        Title = view.findViewById(R.id.mealPlannerTitle);
        mealPlannerRecyclerView = view.findViewById(R.id.mealPlannerRecyclerView);
        mealPlannerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        plannerpresenter = new MealPlannerPresenter(this,mealPlannerLocalDataSource);

        adapter = new MealPlannerAdapter(getContext(), new ArrayList<>(), new DeletPlannedMeal() {
            @Override
            public void OnDelet(MealPlannerDTO mealPlannerDTO) {
                plannerpresenter.removeMealFromPlanner(mealPlannerDTO);
            }
        });
        mealPlannerRecyclerView.setAdapter(adapter);

        recieved = mealPlannerLocalDataSource.getAllPlannedMeals();
        recieved.observe(getViewLifecycleOwner(), new Observer<List<MealPlannerDTO>>() {
            @Override
            public void onChanged(List<MealPlannerDTO> mealPlannerDTOS) {
                adapter.updateMealPlannerList(mealPlannerDTOS);
            }
        });

//        mealTypeSpinner = view.findViewById(R.id.mealTypeSpinner);
//        selectDateButton = view.findViewById(R.id.selectDateButton);
//        addMealButton = view.findViewById(R.id.addMealButton);

//        selectDateButton.setOnClickListener(v -> showDatePicker());
//
//        addMealButton.setOnClickListener(v -> {
//            String mealType = mealTypeSpinner.getSelectedItem().toString();
//            presenter.addMealToPlanner(selectedDate, mealType, 123); // Replace 123 with actual meal ID
//        });
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void showDatePicker() {
//        DatePicker datePicker = new DatePicker(getContext());
//        datePicker.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
//            selectedDate = year + "-" + (monthOfYear + 1)             presenter.loadMealPlansForDate(selectedDate);
//            + "-" + dayOfMonth;
//        });
//    }
    @Override
    public void showMealPlans(List<MealPlannerDTO> mealPlannerList) {
        adapter.updateMealPlannerList(mealPlannerList);
        adapter.notifyDataSetChanged();
        if (mealPlannerList != null && !mealPlannerList.isEmpty()) {
            Log.d(TAG, "showData: Successfully updated with " + mealPlannerList.size() + " planned meals.");
        } else {
            Log.d(TAG, "showData: No planned meals to display.");
        }

    }

    @Override
    public void onMealClick(MealDTO meal) {
        if(meal != null) {
            Intent intent = new Intent(getContext(), MealDetailsActivity.class);
            intent.putExtra("plannedMeal", meal);
            startActivity(intent);
            Log.d(TAG, "Meal clicked: " + meal.getStrMeal() + ", ID: " + meal.getIdMeal());
        }
        else {
            Log.e(TAG2, "Error: MealDTO object is null!");
        }

    }


    @Override
    public void RemovePlannedItem(MealPlannerDTO mealPlannerDTO) {
        int position = adapter.getPlannedMeals().indexOf(mealPlannerDTO);

        adapter.removeItem(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Item Deleted");
        builder.setMessage("Deleted: " + mealPlannerDTO.getStrMeal() + "\nDo you want to undo this action?");

        builder.setPositiveButton("Undo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Restore the item in the adapter's list
                adapter.restoreItem(mealPlannerDTO, position);
                mealPlannerRecyclerView.scrollToPosition(position); // Scroll to the restored item
            }
        });

        builder.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                plannerpresenter.repo.deleteMealPlanned(mealPlannerDTO);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
