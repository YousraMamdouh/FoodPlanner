package com.example.foodplanner.searchByIngredient.View;

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
import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>{

    private static final String Tag="Ingredients Adapter";
    private List<Ingredients> ingredientsList;
    private Context context;

    public void setIngredientsItemsList(List<Ingredients> ingredientsList) {
        this.ingredientsList=ingredientsList;
    }


    public IngredientsAdapter(Context context, List<Ingredients> ingredientsList)
    {
        this.context=context;
        this.ingredientsList=ingredientsList;
        System.out.println("adapter created");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.search_items,parent,false);

        MyViewHolder myViewHolder=new MyViewHolder(view);
        Log.i(Tag,"OnCreateViewHolder");
        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ingredients current = ingredientsList.get(position);
        holder.ingredientName.setText(current.getStrIngredient());
    //    System.out.println("I'm heere can you see me: "+ current.getIdCategory());
       // Glide.with(context).load(current.getStrCategoryThumb()).apply(new RequestOptions().override(150,150)).into(holder.categoryImage);
        Glide.with(context).load(String.format("https://www.themealdb.com/images/ingredients/%s-Small.png", ingredientsList.get(position).getStrIngredient()));

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
            ingredientName =itemView.findViewById(R.id.nameView);
          ingredientImage=itemView.findViewById(R.id.favMealImage);
        }
    }
}
