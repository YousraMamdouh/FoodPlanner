package com.example.foodplanner.mealDetails.view;

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
import com.example.foodplanner.R;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolder>{

    private static final String Tag="Meal Adapter";
    private List<String> ingredientsList;
    private Context context;




    public void setIngredientsList(List<String> ingredientsList) {
        this.ingredientsList=ingredientsList;
    }




   // private OnFavoriteClickListener listener;


    public MealAdapter(Context context, List<String> ingredientsList)
    {
        this.context=context;
        this.ingredientsList=ingredientsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.ingredients_items,parent,false);

        MyViewHolder myViewHolder=new MyViewHolder(view);
        Log.i(Tag,"OnCreateViewHolder");
        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.ingredientName.setText(ingredientsList.get(position));
        System.out.println("inside adapter "+ingredientsList.get(0));
        Glide.with(context).load(String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", ingredientsList.get(position)))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ingredientImage);

        Log.i(Tag,"onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientName;
        ImageView ingredientImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
          ingredientName=itemView.findViewById(R.id.ingredientName);
          ingredientImage=itemView.findViewById(R.id.ingredientImage);
        }
    }
}
