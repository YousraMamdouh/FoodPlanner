package com.example.foodplanner.favorite.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.dataBase.ConcreteLocalSource;
import com.example.foodplanner.favorite.presenter.FavoritePresenterInterface;
import com.example.foodplanner.favorite.presenter.FavoritesPresenter;
import com.example.foodplanner.network.model.MealsDetails;
import com.example.foodplanner.network.model.Repository;
import com.example.foodplanner.network.API_Client;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment implements OnDeleteClickListener ,FavoritesViewInterface{
    RecyclerView favRecyclerView;
    FavoritesAdapter favoritesAdapter;
    LinearLayoutManager layoutManager;
    FavoritePresenterInterface favoritePresenterInterface;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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

        View view= inflater.inflate(R.layout.fragment_favorite, container, false);
        favRecyclerView=view.findViewById(R.id.favRecyclerView);
        favoritePresenterInterface=new FavoritesPresenter(this, Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getActivity()),getActivity()));
        favoritesAdapter=new FavoritesAdapter(getActivity(),new ArrayList<>(),this);
        layoutManager=new LinearLayoutManager(getActivity());
        favRecyclerView.setLayoutManager(layoutManager);
        favRecyclerView.setAdapter(favoritesAdapter);
        favoritePresenterInterface.getMyFavorites();
        return view;
    }

    @Override
    public void onClick(MealsDetails mealsDetails) {
        Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
        deleteMeal(mealsDetails);

    }

    @Override
    public void deleteMeal(MealsDetails mealsDetails) {
        favoritePresenterInterface.removeMealFromFavorite(mealsDetails);

    }

    @Override
    public void showMeals(LiveData<List<MealsDetails>> mealsDetails) {
        mealsDetails.observe(this, new Observer<List<MealsDetails>>() {
            @Override
            public void onChanged(List<MealsDetails> mealsDetails) {
                favoritesAdapter.setList(mealsDetails);
                favoritesAdapter.notifyDataSetChanged();
            }
        });

    }
}