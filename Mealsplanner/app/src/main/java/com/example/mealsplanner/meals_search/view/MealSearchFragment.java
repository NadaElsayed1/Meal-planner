package com.example.mealsplanner.meals_search.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.mealsplanner.R;
import com.example.mealsplanner.meals_search.presenter.MealSearchPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

public class MealSearchFragment extends Fragment implements IMealSearchView {
    private static final String TAG = "MealSearch";
    private static final String TAG2 = "ErrorAtSearch";

    MealLocalDataSource repo;
    RecyclerView search;
    TextView Title;
    RecyclerView.LayoutManager layoutManager;
    MealSearchAdapter mealSearchAdapter;
    MealRemoteDataStructure APIClient;
    MealSearchPresenter mealSearchPresenter;
    NetworkCallback nc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_search, container, false);

        /*Prepare RecyclerView and show data using adapter*/
        Title = view.findViewById(R.id.search_meals_title);
        search = view.findViewById(R.id.SearchRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        search.setLayoutManager(layoutManager);
        mealSearchAdapter = new MealSearchAdapter(getContext(), new ArrayList<>());
        search.setAdapter(mealSearchAdapter);

        /*Network setup*/
        APIClient = MealRemoteDataStructure.getInstance();

        /*Repo instance for all app activities*/
        repo = MealLocalDataSource.getInstance(getContext().getApplicationContext());

        /*Presenter creation by passing the fragment object (which implements the interface)*/
        mealSearchPresenter = new MealSearchPresenter(APIClient, this);
        mealSearchPresenter.searchMeals("Arrabiata");

        return view;
    }

    @Override
    public void showData(List<MealDTO> show) {
        mealSearchAdapter.setList(show);
        mealSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
