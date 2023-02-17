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
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.searchByCountry.model.Countries;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.MyViewHolder>{

    private static final String Tag="Countries Adapter";
    private List<Countries> countriesList;
    private Context context;
    private GetMealsClickListener listener;


    public void setCountriesItemsList(List<Countries> countriesList) {
        this.countriesList = countriesList;
    }
//    public void setCountriesPictures(List<Integer> countriesPictures) {
//        this.countriesPictures=countriesPictures;
//    }


   // private OnFavoriteClickListener listener;


    public CountriesAdapter(Context context, List<Countries> countriesList,GetMealsClickListener listener)
    {
        this.context=context;
        this.listener=listener;
       // this.countriesPictures=countriesPictures;
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
//        Glide.with(context).load(countriesPictures.get(position))
//                .apply(new RequestOptions()
//                        .override(150,150)).into(holder.countryImage);
        String area = current.getStrArea();
        if (area.equals("American")) {
            holder.countryImage.setImageResource(R.drawable.america);
        } else if (area.equals("British")) {
            holder.countryImage.setImageResource(R.drawable.british);
        } else if (area.equals("Canadian")) {
            holder.countryImage.setImageResource(R.drawable.canada);
        } else if (area.equals("Chinese")) {
            holder.countryImage.setImageResource(R.drawable.china);
        } else if (area.equals("Croatian")) {
            holder.countryImage.setImageResource(R.drawable.croatian);
        } else if (area.equals("Dutch")) {
            holder.countryImage.setImageResource(R.drawable.dutch);
        } else if (area.equals("Egyptian")) {
            holder.countryImage.setImageResource(R.drawable.egypt);
        } else if (area.equals("French")) {
            holder.countryImage.setImageResource(R.drawable.french);
        } else if (area.equals("Greek")) {
            holder.countryImage.setImageResource(R.drawable.greek);
        } else if (area.equals("Indian")) {
            holder.countryImage.setImageResource(R.drawable.indian);
        } else if (area.equals("Irish")) {
            holder.countryImage.setImageResource(R.drawable.croatian);
        } else if (area.equals("Jamaican")) {
            holder.countryImage.setImageResource(R.drawable.kenya);
        } else if (area.equals("Japanese")) {
            holder.countryImage.setImageResource(R.drawable.japan);
        } else if (area.equals("Kenyan")) {
            holder.countryImage.setImageResource(R.drawable.kenya);
        } else if (area.equals("Malaysian")) {
            holder.countryImage.setImageResource(R.drawable.malaysian);
        } else if (area.equals("Mexican")) {
            holder.countryImage.setImageResource(R.drawable.mexico);
        } else if (area.equals("Polish")) {
            holder.countryImage.setImageResource(R.drawable.croatian);
        } else if (area.equals("Portuguese")) {
            holder.countryImage.setImageResource(R.drawable.portug);
        } else if (area.equals("Russian")) {
            holder.countryImage.setImageResource(R.drawable.russian);
        } else if (area.equals("Spanish")) {
            holder.countryImage.setImageResource(R.drawable.spani);
        } else if (area.equals("Thai")) {
            holder.countryImage.setImageResource(R.drawable.thia);
        } else if (area.equals("Tunisian")) {
            holder.countryImage.setImageResource(R.drawable.tunisian);
        } else if (area.equals("Turkish")) {
            holder.countryImage.setImageResource(R.drawable.turcia);
        } else if (area.equals("Unknown")) {
            holder.countryImage.setImageResource(R.drawable.unknown);
        } else if (area.equals("Vietnamese")) {
            holder.countryImage.setImageResource(R.drawable.vietnam);
        } else if (area.equals("Moroccan")) {
            holder.countryImage.setImageResource(R.drawable.moroco);
        } else if (area.equals("Italian")) {
            holder.countryImage.setImageResource(R.drawable.italian);
        }
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
