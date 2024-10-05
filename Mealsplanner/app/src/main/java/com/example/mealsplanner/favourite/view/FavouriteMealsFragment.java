package com.example.mealsplanner.favourite.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mealsplanner.R;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.detailed_meals.view.MealDetailsActivity;
import com.example.mealsplanner.favourite.presenter.FavPresenter;
import com.example.mealsplanner.favourite.view.OnMealClickListener;
import com.example.mealsplanner.model.MealDTO;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class FavouriteMealsFragment extends Fragment implements IFavouriteMeals, OnMealClickListener {
    private static final String TAG = "ViewData";
    private MealLocalDataSource repo;
    private RecyclerView favrecy;
    LiveData<List<MealDTO>> received;
    private FavAdapter favAdapter;
    private FavPresenter favPresenter;
    private TextView Title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_meals, container, false);


        Title = view.findViewById(R.id.fav_meals_title);
        favrecy = view.findViewById(R.id.rvFavMeals);

        repo = MealLocalDataSource.getInstance(requireContext());

        favPresenter = new FavPresenter(repo, this);
        favAdapter = new FavAdapter(getContext(), new ArrayList<>(), new FavMealClickListener() {
            @Override
            public void OnClick(MealDTO mealDTO) {
                favPresenter.RemoveItem(mealDTO);
            }
        },this::onMealClick);

        favrecy.setLayoutManager(new LinearLayoutManager(getContext()));
        favrecy.setAdapter(favAdapter);

        received = repo.getAllMeals();
        received.observe(getViewLifecycleOwner(), new Observer<List<MealDTO>>() {
            @Override
            public void onChanged(List<MealDTO> mealDTOS) {
                favAdapter.setList(mealDTOS);
            }
        });

        return view;
    }

    @Override
    public void RemoveItem(MealDTO mealDTO) {
        /*to remove item from adapter's list*/
        int position = favAdapter.getMeals().indexOf(mealDTO); /*get its position @ the list*/
        favAdapter.removeItem(position); /*temp remove from list*/

        /*show the snackbar for undo option*/
        Snackbar snackbar = Snackbar.make(favrecy, "Deleted: " + mealDTO.getStrMeal(), Snackbar.LENGTH_LONG);

        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if Undo is clicked -> reinsert the item into the adapter's list*/
                favAdapter.restoreItem(mealDTO, position);
                favrecy.scrollToPosition(position); // Scroll to the restored item
            }
        });

        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                    /*if the snackbar was skipped without the undo action (really delete)*/
                    favPresenter.repo.delete(mealDTO);
                }
            }
        });

        snackbar.show();
    }


    @Override
    public void onMealClick(MealDTO meal) {
        if (meal != null) {
            Intent intent = new Intent(getContext(), MealDetailsActivity.class);
            intent.putExtra("favouriteMeal", meal);
            startActivity(intent);
            Log.d(TAG, "Meal clicked: " + meal.getStrMeal() + ", ID: " + meal.getIdMeal());
        } else {
            Log.e("Error: ", "Error: MealDTO object is null!");
        }
    }
}
