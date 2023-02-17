package com.example.foodplanner.searchByCountry.View;

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
import com.example.foodplanner.searchByCountry.model.Countries;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.MyViewHolder>{

    private static final String Tag="Countries Adapter";
    private List<Countries> countriesList;
    private Context context;
    private GetMealsClickListener listener;
    private List<Integer> countriesPictures;


    public void setCountriesItemsList(List<Countries> countriesList) {
        this.countriesList = countriesList;
    }
    public void setCountriesPictures(List<Integer> countriesPictures) {
        this.countriesPictures=countriesPictures;
    }


   // private OnFavoriteClickListener listener;


    public CountriesAdapter(Context context, List<Countries> countriesList,List<Integer> countriesPictures,GetMealsClickListener listener)
    {
        this.context=context;
        this.listener=listener;
        this.countriesPictures=countriesPictures;
        this.countriesList=countriesList;
        System.out.println("adapter created");
      //  System.out.println("The first item category "+categoryItemsList.get(0).getStrCategory());

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
        Countries current = countriesList.get(position);
        holder.countryName.setText(current.getStrArea());
        Glide.with(context).load(countriesPictures.get(position))
                .apply(new RequestOptions()
                        .override(150,150)).into(holder.countryImage);
        System.out.println("malyana: "+countriesPictures.get(position));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.getMealsOfClickedCategory(countriesList.get(position).getStrArea());
                System.out.println("Click:"+countriesList.get(position).getStrArea());
            }
        });
        Log.i(Tag,"onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView countryName;
        ImageView countryImage;
        ConstraintLayout layout;
      //  ImageView categoryImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName=itemView.findViewById(R.id.nameView);
            countryImage=itemView.findViewById(R.id.mealImageFav);
            layout=itemView.findViewById(R.id.layout);
        }
    }
}
