package com.example.mealsplanner.favourite.presenter;
import com.example.mealsplanner.db.MealLocalDataSource;
import com.example.mealsplanner.favourite.view.IFavouriteMeals;
import com.example.mealsplanner.model.MealDTO;

public class FavPresenter implements IFavouriteMeals {
    public MealLocalDataSource repo;
    private IFavouriteMeals view;

    public FavPresenter(MealLocalDataSource repository, IFavouriteMeals view) {
        this.repo = repository;
        this.view = view;
    }

    @Override
    public void RemoveItem(MealDTO mealDTO) {
        repo.delete(mealDTO);
        view.RemoveItem(mealDTO);
    }
}
