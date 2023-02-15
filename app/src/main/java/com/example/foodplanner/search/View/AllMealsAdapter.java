package com.example.foodplanner.search.View;

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

public class AllMealsAdapter extends RecyclerView.Adapter<AllMealsAdapter.MyViewHolder>{

    private static final String Tag="All Meals Adapter";
    private List<MealsDetails> allMealsList;
    private Context context;

    private AddToFavoriteClickListener addToFavoriteClickListener;

    public void setAllMealsItemList(List<MealsDetails> allMealsList) {
        this.allMealsList =allMealsList;
    }


   // private OnFavoriteClickListener listener;


    public AllMealsAdapter(Context context, List<MealsDetails> allMealsList,AddToFavoriteClickListener listener)
    {
        this.context=context;
        this.allMealsList = allMealsList;
        this.addToFavoriteClickListener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.search_all_meals,parent,false);

        MyViewHolder myViewHolder=new MyViewHolder(view);
        Log.i(Tag,"OnCreateViewHolder");
        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MealsDetails currentProduct = allMealsList.get(position);
        holder.mealName.setText(currentProduct.getStrMeal());
       holder.mealCountry.setText(currentProduct.getStrArea());
        Glide.with(context).load(currentProduct.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(150,150)).into(holder.mealImage);
        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavoriteClickListener.onClick(currentProduct);
            }
        });
        Log.i(Tag,"onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return allMealsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        TextView mealCountry;
        ImageView mealImage;
        Button favButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage=itemView.findViewById(R.id.mealImageView);
            mealName=itemView.findViewById(R.id.favMealName);
            mealCountry=itemView.findViewById(R.id.favMealCountry);
            favButton=itemView.findViewById(R.id.btnSignup);

        }
    }
}
