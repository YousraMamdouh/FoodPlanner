package com.example.foodplanner.searchByCountry.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.searchByCountry.model.Countries;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.MyViewHolder>{

    private static final String Tag="Countries Adapter";
    private List<Countries> countriesList;
    private Context context;

    public void setCountriesItemsList(List<Countries> countriesList) {
        this.countriesList = countriesList;
    }


   // private OnFavoriteClickListener listener;


    public CountriesAdapter(Context context, List<Countries> countriesList)
    {
        this.context=context;
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
        System.out.println("Bosyely wna bkalemek "+current.getStrArea());
    //    System.out.println("I'm heere can you see me: "+ current.getIdCategory());
//        Glide.with(context).load(current.getStrCategoryThumb())
//                .apply(new RequestOptions()
//                        .override(150,150)).into(holder.categoryImage);
        Log.i(Tag,"onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView countryName;
      //  ImageView categoryImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName=itemView.findViewById(R.id.nameView);
          //  categoryImage=itemView.findViewById(R.id.categoryImage);
        }
    }
}
