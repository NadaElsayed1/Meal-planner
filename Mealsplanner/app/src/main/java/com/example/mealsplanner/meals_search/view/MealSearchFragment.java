package com.example.mealsplanner.meals_search.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.example.mealsplanner.R;
import com.example.mealsplanner.meals_search.presenter.MealSearchPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.model.MealRepository;
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
    RadioGroup radioGroup;
    RadioButton categoryButton , countryButton , ingrediantButton;
    SearchView searchView;
    private MealRepository mealRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_search, container, false);

        Title = view.findViewById(R.id.search_meals_title);
        search = view.findViewById(R.id.SearchRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        search.setLayoutManager(layoutManager);
        mealSearchAdapter = new MealSearchAdapter(getContext(), new ArrayList<>());
        search.setAdapter(mealSearchAdapter);

        mealRepository = new MealRepository(MealRemoteDataStructure.getInstance(),MealLocalDataSource.getInstance(requireContext()));
        mealSearchPresenter = new MealSearchPresenter(mealRepository, this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroup = view.findViewById(R.id.radioGroup);
        categoryButton = view.findViewById(R.id.categoryRadioButton);
        countryButton = view.findViewById(R.id.countryRadioButton2);
        ingrediantButton = view.findViewById(R.id.ingredientRadioButton3);
        searchView = view.findViewById(R.id.searchViewtxt);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                int selected = radioGroup.getCheckedRadioButtonId();

                String searchType;
                if(selected==categoryButton.getId()){
                    mealSearchPresenter.searchByCategory(query);
                    searchType = "category";
                }else if(selected == countryButton.getId()){
                    mealSearchPresenter.searchByCountry(query);
                    searchType = "country";
                }else if(selected == ingrediantButton.getId()){
                    mealSearchPresenter.searchByIngrediant(query);
                    searchType = "ingredient";
                }else{
                    Toast.makeText(getContext(), "Select type of Search", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mealSearchAdapter = new MealSearchAdapter(view.getContext(), new ArrayList<>());
                search.setAdapter(mealSearchAdapter);

                return false;
            }

        });

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
