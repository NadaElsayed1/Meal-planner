package com.example.mealsplanner.meals_countries.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealsplanner.R;
import com.example.mealsplanner.detailed_meals.view.MealDetailsActivity;
import com.example.mealsplanner.model.MealDTO;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealViewHolder> {
    private List<MealDTO> mealList;
    private Context context;
    OnMealClickListener mealClickListener;

    public MealsAdapter(List<MealDTO> mealList, Context context, OnMealClickListener mealClickListener) {
        this.mealList = mealList;
        this.context = context;
        this.mealClickListener = mealClickListener;
    }
    public void setMealList(List<MealDTO> mealList) {
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item2, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        MealDTO meal = mealList.get(position);
        holder.name.setText(meal.getStrMeal());

        Glide.with(context)
                .load(meal.getStrMealThumb())
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MealDetailsActivity.class);
            intent.putExtra("MealDetails", meal); // meal is the selected meal
            context.startActivity(intent);
    });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView meal_description;
        ImageView image;

        MealViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.meal_name2);
            image = itemView.findViewById(R.id.meal_image2);
        }
    }
}
