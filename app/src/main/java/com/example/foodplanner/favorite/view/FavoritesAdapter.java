package com.example.foodplanner.favorite.view;

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
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.MealsDetails;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder>{

    private static final String Tag="Favorites Adapter";
    private List<MealsDetails> favoriteMealsList;
    private Context context;
    private OnDeleteClickListener onDeleteClickListener;




    public FavoritesAdapter(Context context, List<MealsDetails> favoriteMealsList,OnDeleteClickListener listener)
    {
        this.context=context;
        this.favoriteMealsList = favoriteMealsList;
        this.onDeleteClickListener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.favorite_items,parent,false);

        MyViewHolder myViewHolder=new MyViewHolder(view);
        Log.i(Tag,"OnCreateViewHolder");
        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MealsDetails currentProduct = favoriteMealsList.get(position);
        holder.mealName.setText(currentProduct.getStrMeal());
        //Log.i(Tag,"the category: "+currentProduct.getStrCategory());
       // System.out.println("My name is : "+currentProduct.getIdMeal());
       holder.mealCountry.setText(currentProduct.getStrArea());
        Glide.with(context).load(currentProduct.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(150,150)).into(holder.mealImage);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onDeleteClickListener.onClick(currentProduct);
            }
        });
        Log.i(Tag,"onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return favoriteMealsList.size();
    }

    public void setList(List<MealsDetails> mealsDetails) {
        this.favoriteMealsList=mealsDetails;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        TextView mealCountry;
        ImageView mealImage;
        Button deleteButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage=itemView.findViewById(R.id.favImageView);
            mealName=itemView.findViewById(R.id.favMealName);
            mealCountry=itemView.findViewById(R.id.favMealCountry);
            deleteButton=itemView.findViewById(R.id.favButton);

        }
    }
}
