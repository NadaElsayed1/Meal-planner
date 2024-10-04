package com.example.mealsplanner.meals_categories.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mealsplanner.R;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.meals_categories.presenter.MealCategoriesPresenter;
import com.example.mealsplanner.model.CategoryDTO;
import com.example.mealsplanner.model.MealRepository;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import java.util.ArrayList;
import java.util.List;

public class MealCategoriesFragment extends Fragment implements IMealCategoriesFragment, OnCategoryClickListener {

    private RecyclerView recyclerView;
    TextView Title;
    LinearLayoutManager layoutManager;
    MealCategoriesAdapter mealCategoriesAdapter;
    MealCategoriesPresenter mealCategoriesPresenter;
    private MealRepository mealRepository;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_categories, container, false);

        Title = view.findViewById(R.id.categories_title);
        recyclerView = view.findViewById(R.id.recyclerViewCategories);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        mealCategoriesAdapter = new MealCategoriesAdapter(getContext(),new ArrayList<>(),MealCategoriesFragment.this);
        recyclerView.setAdapter(mealCategoriesAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(), MealLocalDataSource.getInstance(requireContext()));
        mealCategoriesPresenter = new MealCategoriesPresenter(mealRepository, this);
        mealCategoriesPresenter.getMealCategories();

        return view;
    }

    @Override
    public void showData(List<CategoryDTO> categories) {
        mealCategoriesAdapter.setList(categories);
        mealCategoriesAdapter.notifyDataSetChanged();
        Log.i("ShowingData", "showData: " + categories.size());
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

    @Override
    public void onLayoutClick(CategoryDTO categoryDTO) {
        Intent intent = new Intent(this.getActivity(), MealCategoryDetailsActivity.class);
        intent.putExtra("MealCategory",categoryDTO);
        startActivity(intent);
    }
}
