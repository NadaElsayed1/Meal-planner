package com.example.mealsplanner.meals_by_categories.view;

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
import com.example.mealsplanner.model.CategoryDTO;
import com.example.mealsplanner.model.MealDTO;

import java.util.ArrayList;
import java.util.List;

public class MealByCategoryAdapter extends RecyclerView.Adapter<MealByCategoryAdapter.ViewHolder>{
    public static final String TAG = "CategoryAdapter";
    private Context context;
    private ArrayList<MealDTO> meals;

    public MealByCategoryAdapter(Context context, ArrayList<MealDTO> meals) {
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
    public MealByCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_row, parent, false);
        return new MealByCategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDTO mealDTO = meals.get(position);
        holder.mealNameTextView.setText(mealDTO.getStrMeal());
        holder.mealDesription.setText(mealDTO.getStrInstructions());
         Glide.with(context)
                 .load(mealDTO.getStrMealThumb())
                 .into(holder.mealImageView);

        Log.d("CategoryAdapter", "Category loaded: " + mealDTO.getStrMeal());
    }



    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mealNameTextView;
        private  ImageView mealImageView;
        private TextView mealDesription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.category_meal_title);
            mealImageView = itemView.findViewById(R.id.category_meal_image);
            mealDesription = itemView.findViewById(R.id.category_meal_description);
        }
    }
}
