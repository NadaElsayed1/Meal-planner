package com.example.mealsplanner.meal_of_the_day.presenter;

import com.example.mealsplanner.network.NetworkCallback;

public interface IMealOfTheDayPresenter {
    void getMealOfTheDay(NetworkCallback networkCallback);
}
