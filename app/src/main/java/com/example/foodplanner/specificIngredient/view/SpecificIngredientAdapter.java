package com.example.foodplanner.specificIngredient.view;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;

import com.example.foodplanner.model.MealsDetails;


import java.util.List;

public class SpecificIngredientAdapter extends RecyclerView.Adapter<SpecificIngredientAdapter.MyViewHolder>{
    private static final String Tag="Specific ingredient Adapter";
    private List<MealsDetails> allMealsList;
    private Context context;

    private AddToFavorite addToFavorite;
    private OnMealClickedListener onMealClickedListener;

    String [] days;
    public void setIngredientItemList(List<MealsDetails> allMealsList) {
        this.allMealsList =allMealsList;
    }





    public SpecificIngredientAdapter(Context context, List<MealsDetails> allMealsList, AddToFavorite addToFavorite, OnMealClickedListener onMealClickedListener)
    {
        this.context=context;
        this.allMealsList = allMealsList;
        this.addToFavorite =addToFavorite;
        this.onMealClickedListener = onMealClickedListener;
    }

    @NonNull
    @Override
    public SpecificIngredientAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.search_all_meals,parent,false);

       SpecificIngredientAdapter.MyViewHolder myViewHolder=new SpecificIngredientAdapter.MyViewHolder(view);
        Log.i(Tag,"OnCreateViewHolder");
        return myViewHolder;


    }



    @Override
    public void onBindViewHolder(@NonNull SpecificIngredientAdapter.MyViewHolder holder, int position) {
        MealsDetails currentProduct = allMealsList.get(position);
        holder.mealName.setText(currentProduct.getStrMeal());
        holder.mealCountry.setText(currentProduct.getStrArea());
        Glide.with(context).load(currentProduct.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(150,150)).into(holder.mealImage);
        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addToFavorite.onClick(currentProduct);
            }
        });
        Log.i(Tag,"onBindViewHolder");
        holder.calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days = new String[]{"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(v.getContext());
                mBuilder.setSingleChoiceItems(days, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // show dialog
                        AlertDialog mDialog = mBuilder.create();
                        mDialog.show();
                    }
                });
                mBuilder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // show dialog
                        AlertDialog mDialog = mBuilder.create();
                        mDialog.show();
                    }
                });
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onMealClickedListener.getMeal(allMealsList.get(position).getStrMeal());
                System.out.println("Click:"+allMealsList.get(position).getStrMeal());
            }
        });
        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavorite.onClick(currentProduct);
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
        Button calButton;
        ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage=itemView.findViewById(R.id.mealImage);
            mealName=itemView.findViewById(R.id.img_name);
            mealCountry=itemView.findViewById(R.id.favMealCountry);
            favButton=itemView.findViewById(R.id.fav);
            calButton=itemView.findViewById(R.id.calenderbtn);
            layout=itemView.findViewById(R.id.layout);

        }
    }

}
