package com.example.mealsplanner.meals_search.view;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealsplanner.R;
import com.example.mealsplanner.meals_search.presenter.MealSearchPresenter;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.network.MealRemoteDataStructure;
import com.example.mealsplanner.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

public class MealSearchActivity extends AppCompatActivity implements IMealSearchActivity {
    private static final String TAG = "MealSearch";
    private static final String TAG2 = "ErrorAtSearch";
    MealLocalDataSource repo;
    RecyclerView search;
    RecyclerView.LayoutManager layoutManager;
    MealSearchAdapter mealSearchAdapter;
    MealRemoteDataStructure APIClient;
    MealSearchPresenter mealSearchPresenter;
    NetworkCallback nc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_search);

        /*Prepare Recycleview and show data using adapter*/
        search = findViewById(R.id.SearchRecyclerView);
        layoutManager = new LinearLayoutManager(MealSearchActivity.this);
        search.setLayoutManager(layoutManager);
        mealSearchAdapter = new MealSearchAdapter(MealSearchActivity.this,new ArrayList<>());
        search.setAdapter(mealSearchAdapter);

        /*Network*/
        /*which needs callback*/
        APIClient = MealRemoteDataStructure.getInstance();

        /*repo instance for all app activities*/
        repo = MealLocalDataSource.getInstance(this.getApplicationContext());

        /*presenter creation by passing the Activity object (which implement the interface) to a reference of interface(Activity interface)*/
        mealSearchPresenter = new MealSearchPresenter(APIClient,this);
        mealSearchPresenter.searchMeals("Arrabiata");
    }

    @Override
    public void showData(List<MealDTO> show) {
        mealSearchAdapter.setList(show);
        mealSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(error).setTitle("An Error Occured");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}