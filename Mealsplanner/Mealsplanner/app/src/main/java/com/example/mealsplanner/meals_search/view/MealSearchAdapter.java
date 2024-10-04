package com.example.mealsplanner.meals_search.view;

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
import com.example.mealsplanner.meals_search.view.MealSearchAdapter;
import com.example.mealsplanner.model.MealDTO;
import java.util.ArrayList;
import java.util.List;

public class MealSearchAdapter extends RecyclerView.Adapter<MealSearchAdapter.ViewHolder>{
    public static final String TAG = "CountryAdapter";
    private Context context;
    private ArrayList<MealDTO> meals;
    private OnMealClickListener listener;

    public MealSearchAdapter(Context context, ArrayList<MealDTO> meals, OnMealClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
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
    public MealSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_row, parent, false);
        return new MealSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDTO mealDTO = meals.get(position);
        holder.mealNameTextView.setText(mealDTO.getStrMeal());
        holder.mealDescription.setText(mealDTO.getStrInstructions());
        Glide.with(context)
                .load(mealDTO.getStrMealThumb())
                .into(holder.mealImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
                {
                    listener.onMealClick(mealDTO);
                }
            }
        });

        Log.d("CountryAdapter", "Country loaded: " + mealDTO.getStrMeal());
    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mealNameTextView;
        private ImageView mealImageView;
        private TextView mealDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.search_meal_title);
            mealImageView = itemView.findViewById(R.id.search_meal_image);
            mealDescription = itemView.findViewById(R.id.search_meal_description);
        }
    }
}
