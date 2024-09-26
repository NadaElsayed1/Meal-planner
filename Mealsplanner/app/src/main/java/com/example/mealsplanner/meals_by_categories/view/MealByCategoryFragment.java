package com.example.mealsplanner.meals_by_categories.view;

import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealsplanner.R;
import com.example.mealsplanner.meals_by_categories.presenter.MealByCategoryPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

public class MealByCategoryFragment extends Fragment implements IMealByCategoryView {
    private static final String TAG = "ViewCategory";
    private static final String TAG2 = "ErrorViewCategory";

    private MealLocalDataSource repo;
    private RecyclerView Categories;
    private TextView Title;
    private RecyclerView.LayoutManager layoutManager;
    private MealByCategoryAdapter mealByCategoryAdapter;
    private MealRemoteDataStructure APIClient;
    private MealByCategoryPresenter mealByCategoryPresenter;
    private NetworkCallback nc;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_by_category, container, false);


        /* Prepare RecyclerView and show data using adapter */
        Title= view.findViewById(R.id.meals_by_category_title);
        Categories = view.findViewById(R.id.GategoryRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        Categories.setLayoutManager(layoutManager);
        mealByCategoryAdapter = new MealByCategoryAdapter(getActivity(), new ArrayList<>());
        Categories.setAdapter(mealByCategoryAdapter);

        /* Network */
        APIClient = MealRemoteDataStructure.getInstance();

        /* Repo instance for all app activities */
        repo = MealLocalDataSource.getInstance(getActivity().getApplicationContext());

        /* Presenter creation by passing Fragment object (which implements the interface) to a reference of the interface */
        mealByCategoryPresenter = new MealByCategoryPresenter(APIClient, this);
        nc = mealByCategoryPresenter;  // Assign the presenter as the callback

        /* Make network call */
        APIClient.getMealsByCategory("Beef", nc);

        return view;
    }

    @Override
    public void showData(List<MealDTO> show) {
        mealByCategoryAdapter.setList(show);
        mealByCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        if (getActivity() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(error).setTitle("An Error Occurred");
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
