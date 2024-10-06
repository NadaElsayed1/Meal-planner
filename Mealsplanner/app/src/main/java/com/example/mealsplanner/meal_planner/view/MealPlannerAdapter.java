package com.example.mealsplanner.meal_planner.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealsplanner.R;
import com.example.mealsplanner.meal_planner.view.OnMealClickListener;
import com.example.mealsplanner.model.MealDTO;
import com.example.mealsplanner.model.MealPlannerDTO;

import java.util.List;

public class MealPlannerAdapter extends RecyclerView.Adapter<MealPlannerAdapter.MealPlannerViewHolder> {
    public static final String TAG = "PlaneAdapter";
    private List<MealPlannerDTO> mealPlannerList;
    private Context context;
    private DeletPlannedMeal listener;
    private OnMealClickListener mealClickListener;


    public MealPlannerAdapter(Context context, List<MealPlannerDTO> mealPlannerList, DeletPlannedMeal listener, OnMealClickListener mealClickListener) {
        this.mealPlannerList = mealPlannerList;
        this.context = context;
        this.listener = listener;
        this.mealClickListener = mealClickListener;
    }

    public void updateMealPlannerList(List<MealPlannerDTO> newMealPlannerList) {
        this.mealPlannerList.clear();
        this.mealPlannerList.addAll(newMealPlannerList);
        notifyDataSetChanged();
    }

    public List<MealPlannerDTO> getPlannedMeals() {
        return mealPlannerList;
    }
    public void removeItem(int position) {
        mealPlannerList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(MealPlannerDTO product, int position) {
        mealPlannerList.add(position, product);
        notifyItemInserted(position);
    }


    @NonNull
    @Override
    public MealPlannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal_planner, parent, false);
        return new MealPlannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealPlannerViewHolder holder, int position) {
        MealPlannerDTO mealPlanner = mealPlannerList.get(position);
        holder.mealName.setText(mealPlanner.getStrMeal());
        holder.dateTextView.setText(mealPlanner.getDate());
        holder.mealTypeTextView.setText(mealPlanner.getMealType());
        if (mealPlanner.getStrMealThumb() != null && !mealPlanner.getStrMealThumb().isEmpty()) {
            Glide.with(context)
                    .load(mealPlanner.getStrMealThumb())
                    .into(holder.plannedMealImage);
            Log.d(TAG, "Meal Image URL: " + mealPlanner.getStrMealThumb());
        } else {
            Log.w(TAG, "Image URL is null or empty for meal: " + mealPlanner.getStrMeal());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mealClickListener != null)
                {
                    mealClickListener.onMealClick(mealPlanner);
                }
            }
        });

        holder.delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnDelet(mealPlanner);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealPlannerList.size();
    }



    public class MealPlannerViewHolder extends RecyclerView.ViewHolder {
        TextView mealTypeTextView;
        TextView dateTextView;
        TextView mealName;
        ImageView delet;
        ImageView plannedMealImage;

        public MealPlannerViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.planned_meal_name);
            mealTypeTextView = itemView.findViewById(R.id.planned_meal_type);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            plannedMealImage = itemView.findViewById(R.id.planned_meal_image);
            delet = itemView.findViewById(R.id.delete);

        }
    }
}
