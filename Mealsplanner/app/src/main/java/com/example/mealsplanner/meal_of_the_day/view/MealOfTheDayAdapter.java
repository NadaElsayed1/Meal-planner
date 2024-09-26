package com.example.mealsplanner.meal_of_the_day.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealsplanner.R;
import com.example.mealsplanner.model.MealDTO;

import java.util.ArrayList;
import java.util.List;

public class MealOfTheDayAdapter extends RecyclerView.Adapter<MealOfTheDayAdapter.ViewHolder>{
    public static final String TAG = "HomeAdapter";
    private Context context;
    private ArrayList<MealDTO> meals;

    public MealOfTheDayAdapter(Context context, ArrayList<MealDTO> meals) {
        this.context = context;
        this.meals = meals;
    }

    public void setList(List<MealDTO> newProducts) {
        this.meals.clear();
        this.meals.addAll(newProducts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealOfTheDayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.random_meal_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealOfTheDayAdapter.ViewHolder holder, int position) {
        MealDTO mealDTO = meals.get(position);
        holder.title.setText(mealDTO.getStrMeal());
        holder.description.setText(mealDTO.getStrInstructions());
        Glide.with(context)
                .load(mealDTO.getStrMealThumb())
                .into(holder.imageView);
        Log.d("Adapter", "Meals loaded: " + meals.size());

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView description;
        public ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_meal_image);
            title = itemView.findViewById(R.id.item_meal_title);
            description = itemView.findViewById(R.id.item_meal_description);
        }
    }
}

