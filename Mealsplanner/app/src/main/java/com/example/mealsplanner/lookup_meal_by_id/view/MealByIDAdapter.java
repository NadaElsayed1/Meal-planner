package com.example.mealsplanner.lookup_meal_by_id.view;

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
import com.example.mealsplanner.R;
import com.example.mealsplanner.model.MealDTO;

import java.util.ArrayList;
import java.util.List;

public class MealByIDAdapter extends RecyclerView.Adapter<MealByIDAdapter.ViewHolder> {
    public static final String TAG = "MealIdAdapter";
    private Context context;
    private ArrayList<MealDTO> meals;

    public MealByIDAdapter(Context context, ArrayList<MealDTO> meals) {
        this.context = context;
        this.meals = meals;
    }

    public void setList(List<MealDTO> newMeals) {
        this.meals.clear();
        if (newMeals != null) {
            this.meals.addAll(newMeals);
        } else {
            Log.e(TAG, "Received null list of meals for this Country");
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealByIDAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_id_row, parent, false);
        return new MealByIDAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealByIDAdapter.ViewHolder holder, int position) {
        MealDTO mealDTO = meals.get(position);

        // Bind other data (e.g., name, image, etc.)
        holder.mealNameTextView.setText(mealDTO.getStrMeal());
        holder.drinkNameTextView.setText(mealDTO.getStrDrinkAlternate());
        holder.categoryNameTextView.setText(mealDTO.getStrCategory());
        holder.areaNameTextView.setText(mealDTO.getStrArea());
        holder.instructionTextView.setText(mealDTO.getStrInstructions());

        // Load image using Glide
        Glide.with(context)
                .load(mealDTO.getStrMealThumb())
                .into(holder.mealImageView);

        // Load YouTube video using WebView
        String videoUrl = mealDTO.getStrYoutube();
        if (videoUrl != null && videoUrl.contains("=")) {
            String videoId = videoUrl.substring(videoUrl.indexOf("=") + 1); // Extract the video ID
            String iframeHtml = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allowfullscreen></iframe>";

            holder.webView.getSettings().setJavaScriptEnabled(true);
            holder.webView.setWebViewClient(new WebViewClient());
            holder.webView.loadData(iframeHtml, "text/html", "utf-8");
        } else {
            Log.e(TAG, "Invalid YouTube URL: " + videoUrl);
        }

        Log.d(TAG, "Loaded: " + mealDTO.getStrMeal());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mealNameTextView;
        private ImageView mealImageView;
        private TextView drinkNameTextView;
        private TextView categoryNameTextView;
        private TextView areaNameTextView;
        private TextView instructionTextView;
        private WebView webView;  // WebView for YouTube video

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.meal_name_text_view);
            drinkNameTextView = itemView.findViewById(R.id.drink_name_text_view);
            categoryNameTextView = itemView.findViewById(R.id.category_name_text_view);
            areaNameTextView = itemView.findViewById(R.id.area_name_text_view);
            instructionTextView = itemView.findViewById(R.id.instruction_text_view);
            mealImageView = itemView.findViewById(R.id.meal_image_view);
            webView = itemView.findViewById(R.id.webview);  // Initialize WebView
        }
    }
}
