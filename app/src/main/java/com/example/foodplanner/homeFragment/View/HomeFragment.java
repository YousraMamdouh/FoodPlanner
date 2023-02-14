package com.example.foodplanner.homeFragment.View;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
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
public class HomeFragment extends Fragment implements HomeMealsViewInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView mealsRecyclerView;
    MealsPresenterInterface mealsPresenterInterface;

    private MealsDetails dailyInspiration;

    CardView inspiration;
    ImageView inspirationImage;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    HomeMealsAdapter mealsAdapter;
    StaggeredGridLayoutManager layoutManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeScreen.
     */
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
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        mealsRecyclerView=view.findViewById(R.id.mealsRecycler);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //todo call api for the daily inspiration then add to presenter constructor
        mealsAdapter=new HomeMealsAdapter(this.getContext(), new ArrayList<>());
        mealsPresenterInterface=new MealsPresenter(this, Repository.getInstance(API_Client.getInstance(),getActivity()));
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
        System.out.println("kkkkkkkkkkkk");
        mealsAdapter.youMightLikeList = mealsDetails;
        mealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDailyInspiration(MealsDetails meal) {
        System.out.println(meal.getStrMealThumb());
        System.out.println("mmmmmmmmmmm");
        this.dailyInspiration = meal;
        Glide.with(this.getContext()).load(meal.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(150,150)).into(inspirationImage);
    }
}