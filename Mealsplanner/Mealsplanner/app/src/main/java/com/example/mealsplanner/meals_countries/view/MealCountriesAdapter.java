package com.example.mealsplanner.meals_countries.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealsplanner.R;
import com.example.mealsplanner.model.MealDTO;
import java.util.ArrayList;
import java.util.List;

public class MealCountriesAdapter extends RecyclerView.Adapter<MealCountriesAdapter.ViewHolder>{
    public static final String TAG = "CountryAdapter";
    private Context context;
    private ArrayList<MealDTO> meals;
    private OnCountryClickListener countryClickListener;


    public MealCountriesAdapter(Context context, ArrayList<MealDTO> meals, OnCountryClickListener listener) {
        this.context = context;
        this.meals = meals;
        countryClickListener = listener;
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
    public MealCountriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*should be changed*/
        View view = LayoutInflater.from(context).inflate(R.layout.counry_list_row, parent, false);
        return new MealCountriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealCountriesAdapter.ViewHolder holder, int position) {
        MealDTO mealDTO = meals.get(position);
        holder.mealNameTextView.setText(mealDTO.getStrArea());

        Log.d(TAG, "Countries loaded: " + mealDTO.getStrArea());
        holder.itemView.setOnClickListener(v -> {
            if (countryClickListener != null) {
                countryClickListener.onCountryClick(mealDTO);
            }
        });
    }



    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mealNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /*should be changed*/
            mealNameTextView = itemView.findViewById(R.id.country_list_title);

        }
    }
}
