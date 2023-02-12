package com.example.foodplanner.searchByCategory.View;

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
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.CategoryItems;
import com.example.foodplanner.model.MealsDetails;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

    private static final String Tag="Category Adapter";
    private Context context;
    private List<CategoryItems> categoryItemsList;
   // private OnFavoriteClickListener listener;


    public CategoryAdapter(Context context, List<CategoryItems> mealList)
    {
        this.context=context;
        this.categoryItemsList =mealList;

    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.search_items,parent,false);

        MyViewHolder myViewHolder=new MyViewHolder(view);
        Log.i(Tag,"OnCreateViewHolder");
        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {
        CategoryItems currentProduct = categoryItemsList.get(position);
        holder.categoryName.setText(currentProduct.getStrCategory());
        Glide.with(context).load(currentProduct.getStrCategoryThumb())
                .apply(new RequestOptions()
                        .override(150,150)).into(holder.categoryImage);
        Log.i(Tag,"onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return categoryItemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        ImageView categoryImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName=itemView.findViewById(R.id.categoryName);
            categoryImage=itemView.findViewById(R.id.categoryImage);
        }
    }
}
