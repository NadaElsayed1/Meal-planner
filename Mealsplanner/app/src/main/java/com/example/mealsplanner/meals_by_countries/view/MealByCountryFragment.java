package com.example.mealsplanner.meals_by_countries.view;

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
import com.example.mealsplanner.meals_by_countries.presenter.MealByCountryPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import java.util.ArrayList;
import java.util.List;

public class MealByCountryFragment extends Fragment implements IMealByCountryView {
    private static final String TAG = "ViewCountryMeal";
    private static final String TAG2 = "ErrorViewCountryMeal";

    RecyclerView Countries;
    TextView Title;
    RecyclerView.LayoutManager layoutManager;
    MealByCountryAdapter mealByCountryAdapter;
    MealRemoteDataStructure APIClient;
    MealByCountryPresenter mealByCountryPresenter;
    private MealRepository mealRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_by_country, container, false);

        Title = view.findViewById(R.id.meals_by_country_title);
        Countries = view.findViewById(R.id.CountryRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        Countries.setLayoutManager(layoutManager);
        mealByCountryAdapter = new MealByCountryAdapter(getContext(), new ArrayList<>());
        Countries.setAdapter(mealByCountryAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(),MealLocalDataSource.getInstance(requireContext()));
        mealByCountryPresenter = new MealByCountryPresenter(mealRepository, this);
        mealByCountryPresenter.getMealsByCountry("Indian");

        return view;
    }

    @Override
    public void showData(List<MealDTO> show) {
        mealByCountryAdapter.setList(show);
        mealByCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
