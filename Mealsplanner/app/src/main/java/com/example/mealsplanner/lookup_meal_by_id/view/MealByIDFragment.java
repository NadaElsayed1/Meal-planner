package com.example.mealsplanner.lookup_meal_by_id.view;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mealsplanner.R;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.lookup_meal_by_id.presenter.MealByIDPresenter;
import com.example.mealsplanner.meals_by_ingredient.presenter.MealByIngredientPresenter;
import com.example.mealsplanner.meals_by_ingredient.view.MealByIngredientAdapter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;

import java.util.ArrayList;
import java.util.List;

public class MealByIDFragment extends Fragment implements IMealByIDFragment {
    private static final String TAG = "ViewMealId";
    private static final String TAG2 = "ErrorViewMealId";

    RecyclerView MealId;
    TextView Title;
    RecyclerView.LayoutManager layoutManager;
    MealByIDAdapter mealByIDAdapter;
    MealByIDPresenter mealByIDPresenter;
    MealRepository mealRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_by_i_d, container, false);

        // Prepare RecyclerView and show data using adapter
        Title = view.findViewById(R.id.Mealid_title);
        MealId = view.findViewById(R.id.MealIdRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        MealId.setLayoutManager(layoutManager);
        mealByIDAdapter = new MealByIDAdapter(getContext(), new ArrayList<>());
        MealId.setAdapter(mealByIDAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(),MealLocalDataSource.getInstance(requireContext()));

        // Presenter creation by passing the fragment object (which implements the interface)
        mealByIDPresenter = new MealByIDPresenter(mealRepository, this);
        mealByIDPresenter.lookupMealById("52772");

        return view;
    }

    @Override
    public void showData(List<MealDTO> show) {
        mealByIDAdapter.setList(show);
        mealByIDAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}