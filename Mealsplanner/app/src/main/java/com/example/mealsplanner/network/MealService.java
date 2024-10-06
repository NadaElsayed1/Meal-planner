package com.example.mealsplanner.network;

import com.example.mealsplanner.model.CategoryResponse;
import com.example.mealsplanner.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    @GET("random.php")
    Call<MealResponse> getMealOfTheDay();

    @GET("filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<MealResponse> getMealsByCountry(@Query("a") String country);

    @GET("search.php")
    Call<MealResponse> searchMeals(@Query("s") String searchQuery);

    @GET("categories.php")
    Call<CategoryResponse> getMealCategories();

    @GET("list.php?a=list")
    Call<MealResponse> getMealCountries();

    @GET("list.php?i=list")
    Call<MealResponse> getMealIngredients();

    @GET("filter.php")
    Call<MealResponse> filterMealsByIngredient(@Query("i") String ingredient);

    @GET("lookup.php")
    Call<MealResponse> lookupMealById(@Query("i") String mealId);

}

