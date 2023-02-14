package com.example.foodplanner.searchByCategory.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.searchByCategory.model.Categories;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

    private static final String Tag="Category Adapter";
    private List<Categories> categoriesList;
    private Context context;
    private GetMealsClickListener listener;
  //  private CategoriesViewInterface listener;

    public void setCategoryItemsList(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }


   // private OnFavoriteClickListener listener;


    public CategoryAdapter(Context context, List<Categories> mealList, GetMealsClickListener listener)
    {
        this.context=context;
        this.categoriesList =mealList;
        this.listener=listener;
        System.out.println("adapter created");
       // this.listener=listener;
      //  System.out.println("The first item category "+categoryItemsList.get(0).getStrCategory());

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
        Categories currentProduct = categoriesList.get(position);
        holder.categoryName.setText(currentProduct.getStrCategory());
        System.out.println("I'm heere can you see me: "+currentProduct.getIdCategory());
        Glide.with(context).load(currentProduct.getStrCategoryThumb())
                .apply(new RequestOptions()
                        .override(150,150)).into(holder.categoryImage);
        Log.i(Tag,"onBindViewHolder");
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    listener.getMealsOfClickedCategory(currentProduct.getStrCategory());
               listener.getMealsOfClickedCategory(categoriesList.get(position).getStrCategory());
                System.out.println("Click:"+categoriesList.get(position).getStrCategory());
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
ConstraintLayout layout;
        TextView categoryName;
        ImageView categoryImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName=itemView.findViewById(R.id.nameView);
            categoryImage=itemView.findViewById(R.id.mealImageFav);
            layout=itemView.findViewById(R.id.layout);

        }
    }
}
