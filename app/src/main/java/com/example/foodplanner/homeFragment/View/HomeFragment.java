package com.example.foodplanner.homeFragment.View;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.dataBase.ConcreteLocalSource;
import com.example.foodplanner.homeFragment.View.HomeFragmentDirections;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.homeFragment.presenter.MealsPresenterInterface;
import com.example.foodplanner.homeFragment.presenter.MealsPresenter;


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

    CardView inspiration;
    View view;
    ImageView inspirationImage;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    HomeMealsAdapter mealsAdapter;
    StaggeredGridLayoutManager layoutManager;

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
        mealsPresenterInterface.getMeals();
        mealsPresenterInterface.getDailyInspiration();
        return  view;
    }

    @Override
    public void showMeals(List<MealsDetails> mealsDetails) {
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
    public void getMeal(String mealName) {
        com.example.foodplanner.homeFragment.View.HomeFragmentDirections.ActionHomeScreenToMealDetailsFragment action = HomeFragmentDirections.actionHomeScreenToMealDetailsFragment(mealName);
        Navigation.findNavController(view).navigate(action);
    }
}