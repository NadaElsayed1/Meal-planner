package com.example.mealsplanner.meals_categories.view;

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

import java.util.ArrayList;
import java.util.List;

public class MealCategoriesAdapter extends RecyclerView.Adapter<MealCategoriesAdapter.ViewHolder> {

    private ArrayList<CategoryDTO> categories;
    private Context context;
    public static final String TAG = "CategoryAdapterImg";
    private OnCategoryClickListener categoryClickListener;

    public MealCategoriesAdapter(Context context, ArrayList<CategoryDTO> categories,OnCategoryClickListener categoryClickListener) {
        this.categories = categories;
        this.context = context;
        this.categoryClickListener = categoryClickListener;
    }
    public void setList(List<CategoryDTO> newProducts) {
        this.categories.clear();
        if (newProducts != null) {
            this.categories.addAll(newProducts);
        } else {
            Log.e(TAG, "Received null list of categories");
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MealCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item_row, parent, false);
        return new MealCategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealCategoriesAdapter.ViewHolder holder, int position) {
        CategoryDTO categoryDTO = categories.get(position);
        holder.textViewCategoryName.setText(categoryDTO.getStrCategory());

        Glide.with(context)
                .load(categoryDTO.getStrCategoryThumb())
                .into(holder.imageViewCategoryThumb);
        Log.d("CategoryAdapterImg", "Category loaded: " + categoryDTO.getStrCategory());

        holder.itemView.setOnClickListener(v -> {
            if (categoryClickListener != null) {
                categoryClickListener.onLayoutClick(categoryDTO);
            }
        });

    }
    @Override
    public int getItemCount() {
        return categories.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCategoryName;
        private ImageView imageViewCategoryThumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategoryName = itemView.findViewById(R.id.textViewCategoryName);
            imageViewCategoryThumb = itemView.findViewById(R.id.imageViewCategoryThumb);
        }
    }
}

