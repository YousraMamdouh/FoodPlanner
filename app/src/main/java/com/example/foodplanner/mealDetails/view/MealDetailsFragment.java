package com.example.foodplanner.mealDetails.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.dataBase.ConcreteLocalSource;
import com.example.foodplanner.mealDetails.presenter.MealPresenter;
import com.example.foodplanner.mealDetails.presenter.MealPresenterInterface;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.searchByIngredient.model.Ingredients;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MealDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealDetailsFragment extends Fragment implements AddToFavorites,MealViewInterface{

    RecyclerView recyclerView;
    MealAdapter adapter;
   LinearLayoutManager layoutManager;
   MealPresenterInterface mealPresenterInterface;
   ImageView mealImage;
   TextView mealName;
   TextView recipeView;
   TextView mealCountry;
    YouTubePlayerView youTubePlayerView;
   Button addToFavButton;
   View view;
   MealsDetails mealObject;
   List<Ingredients> ingredientsList;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MealDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MealDetailsFragment newInstance(String param1, String param2) {
        MealDetailsFragment fragment = new MealDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view= inflater.inflate(R.layout.fragment_meal_details, container, false);
       mealName=view.findViewById(R.id.myName);
       mealImage=view.findViewById(R.id.myImage);
       youTubePlayerView =view.findViewById(R.id.videoView);
       recipeView=view.findViewById(R.id.recipeView);
       recyclerView=view.findViewById(R.id.myRecyclerView);
       addToFavButton=view.findViewById(R.id.addToFavButton);
       mealCountry=view.findViewById(R.id.myCountry);
       layoutManager=new LinearLayoutManager(getActivity());
       adapter=new MealAdapter(getActivity(),new ArrayList<>());
       mealPresenterInterface=new MealPresenter(this, Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getActivity()),getActivity()),MealDetailsFragmentArgs.fromBundle(getArguments()).getMealName());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        mealPresenterInterface.getMeal();


       return  view;
    }

    @Override
    public void onClick(MealsDetails currentMeal) {
        Toast.makeText(getActivity(), "Meal added to favorites", Toast.LENGTH_SHORT).show();
        addMealToFavorites(currentMeal);
    }



    @Override
    public void showIngredients(List<Ingredients> ingredients) {
        adapter.setIngredientsList(ingredients);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMealDetails(MealsDetails mealsDetails) {
       mealObject=mealsDetails;
        mealName.setText(mealObject.getStrMeal());
        recipeView.setText(mealObject.getStrInstructions());
        mealCountry.setText(mealObject.getStrArea());

        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = mealObject.getStrYoutube();
                System.out.println("URL:"+mealObject.getStrYoutube());
                youTubePlayer.loadVideo(videoId, 0);
                youTubePlayer.play();
            }
        });

        Glide.with(getActivity()).load(mealObject.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(150,150)).into(mealImage);




    }


    @Override
    public void addMealToFavorites(MealsDetails mealsDetails) {
        mealPresenterInterface.addToFavorites(mealsDetails);
    }
}