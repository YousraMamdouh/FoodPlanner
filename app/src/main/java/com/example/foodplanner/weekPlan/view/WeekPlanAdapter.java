package com.example.foodplanner.weekPlan.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.favorite.view.OnDeleteClickListener;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.searchSpecificCategory.view.SpecificCategoryAdapter;

import java.util.List;


public class WeekPlanAdapter  extends RecyclerView.Adapter<WeekPlanAdapter.ViewHolder>{
    private static final String Tag="Plan Adapter";
    private List<MealsDetails> planMealsList;
    private Context context;
    private OnMealClickedListener onMealClickedListener;
    public static  final int INVISIBLE = 0;


    public WeekPlanAdapter (Context context, OnMealClickedListener OnMealsClickListener) {
        super();
        this.context = context;
        this.onMealClickedListener = onMealClickedListener;


    }
    public void setOnMealClickedListener(OnMealClickedListener onMealClickedListener){
        this.onMealClickedListener = onMealClickedListener;
    }

    public void setAllMeals(List<MealsDetails> planMealsList) {

        this.planMealsList = planMealsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favorite_items, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
      ViewHolder ViewHolder=new ViewHolder(view);
        Log.i(Tag,"OnCreateViewHolder");
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsDetails meal = planMealsList.get(position);
        System.out.println(holder+"holder"+meal.getStrMeal());
        holder.mealNameTxt.setText(meal.getStrMeal());
        holder.mealCountryTxt.setText(meal.getStrArea());
        Glide.with(context).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(150, 150))
                .into(holder.mealImageViewPlannerCard);

//        holder.favBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//
//        });
      //holder.favBtn.setVisibility(View.INVISIBLE);
        Log.i(Tag,"onBindViewHolder");

        holder.calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickedListener.deleteMealFromDay(meal);
            }
        });
      //  holder.calendar.setVisibility(View.INVISIBLE);
    }


    @Override
    public int getItemCount() {
        return planMealsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mealNameTxt;
        TextView mealCountryTxt;
        ImageView  mealImageViewPlannerCard;
        Button favBtn;
        Button calendar;
        ConstraintLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
         // favBtn = itemView.findViewById(R.id.fav);
            mealImageViewPlannerCard = itemView.findViewById(R.id.favImageView);
            mealNameTxt = itemView.findViewById(R.id.img_name);
            mealCountryTxt = itemView.findViewById(R.id.favMealCountry);
            layout = itemView.findViewById(R.id.layout);
            calendar = itemView.findViewById(R.id.fav);


        }
    }


        }
