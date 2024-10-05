package com.example.mealsplanner.meal_of_the_day.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.mealsplanner.R;
import com.example.mealsplanner.model.MealDTO;
import java.util.ArrayList;
import java.util.List;

public class MealOfTheDayAdapter extends RecyclerView.Adapter<MealOfTheDayAdapter.ViewHolder> {
    public static final String TAG = "HomeAdapter";
    private Context context;
    private ArrayList<MealDTO> meals;
    private OnMealClickListener listener;

    public MealOfTheDayAdapter(Context context, ArrayList<MealDTO> meals, OnMealClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    public void setList(List<MealDTO> newMeals) {
        this.meals.clear();
        this.meals.addAll(newMeals);
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
        holder.mealNameTextView.setText(mealDTO.getStrMeal());
        Glide.with(context)
                .load(mealDTO.getStrMealThumb())
                .transform(new RoundedCorners(16))
                .into(holder.mealImageView);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMealClick(mealDTO);
            }
        });
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
            mealNameTextView = itemView.findViewById(R.id.item_meal_title);
            mealImageView = itemView.findViewById(R.id.item_meal_image);
        }

    }
}

