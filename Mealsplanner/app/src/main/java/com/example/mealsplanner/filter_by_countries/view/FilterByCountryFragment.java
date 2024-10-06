package com.example.mealsplanner.filter_by_countries.view;

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
import com.example.mealsplanner.filter_by_countries.presenter.FilterByCountryPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import java.util.ArrayList;
import java.util.List;

public class FilterByCountryFragment extends Fragment implements IFilterByCountryView {

    RecyclerView Countries;
    TextView Title;
    RecyclerView.LayoutManager layoutManager;
    FilterByCountryAdapter filterByCountryAdapter;
    MealRemoteDataStructure APIClient;
    FilterByCountryPresenter mealByCountryPresenter;
    private MealRepository mealRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_by_country, container, false);}

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Title = view.findViewById(R.id.meals_by_country_title);
        Countries = view.findViewById(R.id.CountryRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        Countries.setLayoutManager(layoutManager);
        filterByCountryAdapter = new FilterByCountryAdapter(getContext(), new ArrayList<>());
        Countries.setAdapter(filterByCountryAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(),MealLocalDataSource.getInstance(requireContext()));
        mealByCountryPresenter = new FilterByCountryPresenter(mealRepository, this);
        mealByCountryPresenter.getMealsByCountry("Indian");
    }

    @Override
    public void showData(List<MealDTO> show) {
        filterByCountryAdapter.setList(show);
        filterByCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
