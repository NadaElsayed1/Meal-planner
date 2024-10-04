package com.example.mealsplanner.meals_by_ingredient.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealsplanner.R;
import com.example.mealsplanner.meals_by_countries.view.MealByCountryAdapter;
import com.example.mealsplanner.model.MealDTO;

import java.util.ArrayList;
import java.util.List;

public class MealByIngredientAdapter extends RecyclerView.Adapter<MealByIngredientAdapter.ViewHolder>{
    public static final String TAG = "CountryAdapter";
    private Context context;
    private ArrayList<MealDTO> meals;

    public MealByIngredientAdapter(Context context, ArrayList<MealDTO> meals) {
        this.context = context;
        this.meals = meals;
    }

    public void setList(List<MealDTO> newProducts) {
        this.meals.clear();
        if (newProducts != null) {
            this.meals.addAll(newProducts);
        } else {
            Log.e(TAG, "Received null list of meals for this Country");
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealByIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_row, parent, false);
        return new MealByIngredientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealByIngredientAdapter.ViewHolder holder, int position) {
        MealDTO mealDTO = meals.get(position);
        holder.mealNameTextView.setText(mealDTO.getStrMeal());
        Glide.with(context)
                .load(mealDTO.getStrMealThumb())
                .into(holder.mealImageView);

        Log.d("IngredientFilterAdapter", "IngredientFilter loaded: " + mealDTO.getStrMeal());
    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mealNameTextView;
        private ImageView mealImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.ingredient_filter_meal_title);
            mealImageView = itemView.findViewById(R.id.ingredient_filter_meal_image);
        }
    }
}
