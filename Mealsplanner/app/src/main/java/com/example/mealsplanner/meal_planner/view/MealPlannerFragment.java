package com.example.mealsplanner.meal_planner.view;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealsplanner.R;
import com.example.mealsplanner.db.MealPlannerLocalDataSource;
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
    private MealRepository mealRepository;
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

    }


    @Override
    public void RemovePlannedItem(MealPlannerDTO mealPlannerDTO) {
        /*to remove item from adapter's list*/
        int position = adapter.getPlannedMeals().indexOf(mealPlannerDTO); /*get its position @ the list*/
        adapter.removeItem(position); /*temp remove from list*/

        /*show the snackbar for undo option*/
        Snackbar snackbar = Snackbar.make(mealPlannerRecyclerView, "Deleted: " + mealPlannerDTO.getStrMeal(), Snackbar.LENGTH_LONG);

        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if Undo is clicked -> reinsert the item into the adapter's list*/
                adapter.restoreItem(mealPlannerDTO, position);
                mealPlannerRecyclerView.scrollToPosition(position); // Scroll to the restored item
            }
        });

        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                    /*if the snackbar was skipped without the undo action (really delete)*/
                    plannerpresenter.repo.deleteMealPlanned(mealPlannerDTO);
                }
            }
        });

        snackbar.show();

    }
}
