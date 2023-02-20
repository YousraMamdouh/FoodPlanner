package com.example.foodplanner.homeFragment.View;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.dataBase.ConcreteLocalSource;
import com.example.foodplanner.homeFragment.presenter.MealsPresenter;
import com.example.foodplanner.homeFragment.presenter.MealsPresenterInterface;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.API_Client;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeMealsViewInterface,AddToFavoriteClickListener, OnMealClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView mealsRecyclerView;
    MealsPresenterInterface mealsPresenterInterface;

    private MealsDetails dailyInspiration;

    OnMealClickListener onMealClickListener;
    CardView inspiration;
    View view;
    MealsDetails mealsDetails;
    ImageView inspirationImage;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    AddToFavoriteClickListener addToFavoriteClickListener = this;


    HomeMealsAdapter mealsAdapter;
    StaggeredGridLayoutManager layoutManager;
    Button showMoreDetails;
    Button AddToFav;



    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
       view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        mealsRecyclerView=view.findViewById(R.id.mealsRecycler);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //todo call api for the daily inspiration then add to presenter constructor
        mealsAdapter=new HomeMealsAdapter(getActivity(),new ArrayList<>(),this,this);
        mealsPresenterInterface=new MealsPresenter(this,Repository.getInstance(API_Client.getInstance(),ConcreteLocalSource.getInstance(getActivity()),getActivity()));
        mealsRecyclerView.setLayoutManager(layoutManager);
        mealsRecyclerView.setAdapter(mealsAdapter);
        inspiration = view.findViewById(R.id.inspirationCardView);
        inspirationImage = view.findViewById(R.id.inspirationImage);
       // showMoreDetails = view.findViewById(R.id.show_more_btn);
        AddToFav = view.findViewById(R.id.fav_daily_btn);
        mealsPresenterInterface.getMeals();
        mealsPresenterInterface.getDailyInspiration();
        inspirationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getMeal(dailyInspiration.getStrMeal());
            }
        });

        AddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               mealsPresenterInterface.addToFavorites(dailyInspiration);
                Toast.makeText(getActivity(), "Meal added to favorites", Toast.LENGTH_SHORT).show();
            }
        });

        return  view;


    }

//    @Override
//    public void onStop() {
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(changeNetworkListener,filter);
//
//        super.onStop();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//    }

    @Override
    public void showMeals(List<MealsDetails> mealsDetails) {
        this.mealsDetails = mealsDetails.get(0);
        System.out.println(mealsDetails);
        mealsAdapter.youMightLikeList = mealsDetails;
        mealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDailyInspiration(MealsDetails meal) {
        System.out.println(meal.getStrMealThumb());
        this.dailyInspiration = meal;
        Glide.with(this.getContext()).load(meal.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(150,150)).into(inspirationImage);
    }
    public void addMealToFavorites(MealsDetails mealsDetails) {
        mealsPresenterInterface.addToFavorites(mealsDetails);
    }

    @Override
    public void onClick(MealsDetails currentMeal) {
        Toast.makeText(getActivity(), "Meal added to favorites", Toast.LENGTH_SHORT).show();
        addMealToFavorites(currentMeal);
    }

    @Override
    public void onClickDailyInspiration() {
        Toast.makeText(getActivity(), "Meal added to favorites", Toast.LENGTH_SHORT).show();
        addMealToFavorites(mealsDetails);
    }

    @Override
    public void getMeal(String mealName) {
        com.example.foodplanner.homeFragment.View.HomeFragmentDirections.ActionHomeScreenToMealDetailsFragment action = HomeFragmentDirections.actionHomeScreenToMealDetailsFragment(mealName);
        Navigation.findNavController(view).navigate(action);
    }
}