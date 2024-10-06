package com.example.mealsplanner.meal_ingredients.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealsplanner.R;
import com.example.mealsplanner.meals_countries.view.MealCountriesAdapter;
import com.example.mealsplanner.model.MealDTO;

import java.util.ArrayList;
import java.util.List;

public class MealIngredientAdapter extends RecyclerView.Adapter<MealIngredientAdapter.ViewHolder>{
    public static final String TAG = "IngredientAdapter";
    private Context context;
    private ArrayList<MealDTO> meals;

    public MealIngredientAdapter(Context context, ArrayList<MealDTO> meals) {
        this.context = context;
        this.meals = meals;
    }

    public void setList(List<MealDTO> newProducts) {
        this.meals.clear();
        if (newProducts != null) {
            this.meals.addAll(newProducts);
        } else {
            Log.e(TAG, "Received null list of meals");
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_list_row, parent, false);
        return new MealIngredientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealIngredientAdapter.ViewHolder holder, int position) {
        MealDTO mealDTO = meals.get(position);
        holder.mealNameTextView.setText(mealDTO.getStrIngredient());

        Log.d(TAG, "Ingredient loaded: " + mealDTO.getStrIngredient());
    }



    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mealNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.ingredient_list_title);

        }
    }
}
