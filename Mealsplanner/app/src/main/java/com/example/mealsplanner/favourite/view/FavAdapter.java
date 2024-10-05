package com.example.mealsplanner.favourite.view;

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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.mealsplanner.R;
import com.example.mealsplanner.favourite.view.OnMealClickListener;
import com.example.mealsplanner.model.MealDTO;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private Context context;
    private List<MealDTO> mealDTO;
    private FavMealClickListener listener;
    public static final String TAG = "favAdapter";
    private OnMealClickListener mealClickListener;


    public FavAdapter(Context context, List<MealDTO> productList, FavMealClickListener _listener, OnMealClickListener mealClickListener) {
        this.context = context;
        this.mealDTO = productList;
        this.listener = _listener;
        this.mealClickListener = mealClickListener;
    }

    public void setList(List<MealDTO> updatedProducts) {
        this.mealDTO = updatedProducts;
        notifyDataSetChanged();
    }
    /*to remove an item from list temporarily*/
    public void removeItem(int position) {
        mealDTO.remove(position);
        notifyItemRemoved(position);
    }

    /*to restore*/
    public void restoreItem(MealDTO product, int position) {
        mealDTO.add(position, product);
        notifyItemInserted(position);
    }


    /*we get data here to display it using adapter*/
    public List<MealDTO> getMeals() {
        return mealDTO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favourite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDTO mealDTO = this.mealDTO.get(position);
        holder.mealNameTextView.setText(mealDTO.getStrMeal());
        Glide.with(context)
                .load(mealDTO.getStrMealThumb())
                .transform(new RoundedCorners(16))
                .into(holder.mealImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mealClickListener != null)
                {
                    mealClickListener.onMealClick(mealDTO);
                }
            }
        });
        holder.removeFavbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.OnClick(mealDTO);
        }
        });
        Log.d("CategoryAdapter", "Category loaded: " + mealDTO.getStrMeal());
    }


    @Override
    public int getItemCount() {
        return mealDTO.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mealNameTextView;
        private  ImageView mealImageView;
        private ImageView removeFavbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.favourite_meal_title);
            mealImageView = itemView.findViewById(R.id.favourite_meal_image);
            removeFavbtn = itemView.findViewById(R.id.removeFavbtn);
        }
    }
}
