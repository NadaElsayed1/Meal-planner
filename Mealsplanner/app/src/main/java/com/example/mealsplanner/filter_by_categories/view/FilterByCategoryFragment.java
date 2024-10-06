package com.example.mealsplanner.filter_by_categories.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealsplanner.R;
import com.example.mealsplanner.filter_by_categories.presenter.FilterByCategoryPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import java.util.ArrayList;
import java.util.List;

public class FilterByCategoryFragment extends Fragment implements IFilterByCategoryView {

    private RecyclerView Categories;
    private RecyclerView.LayoutManager layoutManager;
    private FilterByCategoryAdapter filterByCategoryAdapter;
    private TextView Title;
    private FilterByCategoryPresenter filterByCategoryPresenter;
    private MealRepository mealRepository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_by_category, container, false);}

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Title= view.findViewById(R.id.meals_by_category_title);
        Categories = view.findViewById(R.id.GategoryRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        Categories.setLayoutManager(layoutManager);
        filterByCategoryAdapter = new FilterByCategoryAdapter(getActivity(), new ArrayList<>());
        Categories.setAdapter(filterByCategoryAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(),MealLocalDataSource.getInstance(requireContext()));

        filterByCategoryPresenter = new FilterByCategoryPresenter(mealRepository, this);
        filterByCategoryPresenter.getMealsByCategory("Beef");
    }

    @Override
    public void showData(List<MealDTO> show) {
        filterByCategoryAdapter.setList(show);
        filterByCategoryAdapter.notifyDataSetChanged();
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
