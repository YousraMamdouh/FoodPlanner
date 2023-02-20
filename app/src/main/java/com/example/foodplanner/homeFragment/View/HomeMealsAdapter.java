package com.example.foodplanner.homeFragment.View;

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
import com.example.foodplanner.model.MealsDetails;



import java.util.List;

public class HomeMealsAdapter extends RecyclerView.Adapter<HomeMealsAdapter.MyViewHolder> {

    public List<MealsDetails> youMightLikeList;

    private Context context;
    private OnMealClickListener mealClickedListener;
    private AddToFavoriteClickListener listener;

    public HomeMealsAdapter(Context context, List<MealsDetails> youMightLikeList, AddToFavoriteClickListener listener, OnMealClickListener mealClickedListener)
    {
        this.context=context;
        this.youMightLikeList = youMightLikeList;
        this.listener =listener;
        this.mealClickedListener = mealClickedListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.you_might_like_img,parent,false);

        MyViewHolder myViewHolder=new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeMealsAdapter.MyViewHolder holder, int position) {
        MealsDetails currentProduct = youMightLikeList.get(holder.getAdapterPosition());
        holder.mealName.setText(currentProduct.getStrMeal());
        Glide.with(context).load(currentProduct.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(150,150)).into(holder.mealImage);


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               mealClickedListener.getMeal(youMightLikeList.get(position).getStrMeal());


            }
        });

        Log.i("Tag","onBindViewHolder");
    }


    @Override
    public int getItemCount() {
        return youMightLikeList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;

        ImageView mealImage;
        ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage=itemView.findViewById(R.id.mealImage);
            mealName=itemView.findViewById(R.id.img_name);

            layout=itemView.findViewById(R.id.layout);

        }
    }
}
