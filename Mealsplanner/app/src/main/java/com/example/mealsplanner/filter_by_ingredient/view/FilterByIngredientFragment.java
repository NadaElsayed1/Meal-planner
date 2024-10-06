package com.example.mealsplanner.filter_by_ingredient.view;

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
import com.example.mealsplanner.filter_by_ingredient.presenter.FilterByIngredientPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import java.util.ArrayList;
import java.util.List;

public class FilterByIngredientFragment extends Fragment implements IFilterByIngredientFragment {
    private static final String TAG = "ViewIngredientMeal";
    private static final String TAG2 = "ErrorViewIngredientMeal";

    RecyclerView IngredientsFilter;
    TextView Title;
    RecyclerView.LayoutManager layoutManager;
    FilterByIngredientAdapter filterByIngredientAdapter;
    MealRepository mealRepository;
    FilterByIngredientPresenter filterByIngredientPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_by_ingredient, container, false);}

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Title = view.findViewById(R.id.ingredients_title);
        IngredientsFilter = view.findViewById(R.id.IngredientsRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        IngredientsFilter.setLayoutManager(layoutManager);
        filterByIngredientAdapter = new FilterByIngredientAdapter(getContext(), new ArrayList<>());
        IngredientsFilter.setAdapter(filterByIngredientAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(),MealLocalDataSource.getInstance(requireContext()));
        filterByIngredientPresenter = new FilterByIngredientPresenter(mealRepository, this);
        filterByIngredientPresenter.filterMealsByIngredient("Salmon");
    }

    @Override
    public void showData(List<MealDTO> show) {
        filterByIngredientAdapter.setList(show);
        filterByIngredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}