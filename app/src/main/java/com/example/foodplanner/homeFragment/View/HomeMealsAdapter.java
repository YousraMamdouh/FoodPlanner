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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.search.View.AllMealsAdapter;

import java.util.List;

public class HomeMealsAdapter extends RecyclerView.Adapter<HomeMealsAdapter.MyViewHolder> {

    public List<MealsDetails> youMightLikeList;

    private Context context;

    public HomeMealsAdapter(Context context, List<MealsDetails> youMightLikeList)
    {
        this.context=context;
        this.youMightLikeList = youMightLikeList;
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
        MealsDetails currentProduct = youMightLikeList.get(position);
        holder.mealName.setText(currentProduct.getStrMeal());
        Glide.with(context).load(currentProduct.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(150,150)).into(holder.mealImage);
    }

    @Override
    public int getItemCount() {
        return youMightLikeList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mealName;
        Button fav;
        ImageView mealImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage=itemView.findViewById(R.id.mealImage);
            mealName=itemView.findViewById(R.id.img_name);
           fav=itemView.findViewById(R.id.fav);

        }
    }
}
