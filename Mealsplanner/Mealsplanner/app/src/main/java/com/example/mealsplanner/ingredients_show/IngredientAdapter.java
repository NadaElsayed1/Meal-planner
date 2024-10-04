package com.example.mealsplanner.ingredients_show;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mealsplanner.R;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private final List<String> ingredients;
    private final List<String> measures;
    private final Context context;

    public IngredientAdapter(Context context, List<String> ingredients, List<String> measures) {
        this.context = context;
        this.ingredients = ingredients;
        this.measures = measures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ingredient = ingredients.get(position);
        String measure = measures.get(position);

        holder.ingredientName.setText(ingredient);
        holder.measure.setText(measure);

        // Load ingredient image
        String ingredientImageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient + ".png";
        Glide.with(context)
                .load(ingredientImageUrl)
                .into(holder.ingredientImage);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        TextView measure;
        ImageView ingredientImage;

        ViewHolder(View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
            measure = itemView.findViewById(R.id.ingredient_measure);
            ingredientImage = itemView.findViewById(R.id.ingredient_image);
        }
    }
}
