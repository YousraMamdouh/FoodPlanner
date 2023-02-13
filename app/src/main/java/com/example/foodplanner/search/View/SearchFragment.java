package com.example.foodplanner.search.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.foodplanner.R;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.search.presentor.AllMealsPresenter;
import com.example.foodplanner.search.presentor.AllMealsPresenterInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements AllMealsViewInterface {
Button cuisineButton;
Button categoryButton;
Button ingredientButton;
RecyclerView allMealsRecyclerView;
AllMealsAdapter allMealsAdapter;
StaggeredGridLayoutManager layoutManager;
AllMealsPresenterInterface allMealsPresenterInterface;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
       View view=inflater.inflate(R.layout.fragment_search_screen, container, false);

       cuisineButton=view.findViewById(R.id.cuisineButton);
       categoryButton=view.findViewById(R.id.categoryButton);
       ingredientButton=view.findViewById(R.id.ingredientsButton);
       allMealsRecyclerView=view.findViewById(R.id.allMealsRecyclerView);
    //   layoutManager=new LinearLayoutManager(getActivity());
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
       allMealsAdapter=new AllMealsAdapter(getActivity(),new ArrayList<>());
       allMealsPresenterInterface=new AllMealsPresenter(this, Repository.getInstance(API_Client.getInstance(),getActivity()));
       allMealsRecyclerView.setLayoutManager(layoutManager);
       allMealsRecyclerView.setAdapter(allMealsAdapter);
       allMealsPresenterInterface.getMeals();
       cuisineButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(view).navigate(R.id.action_searchScreen_to_searchByCountryFragment);

           }
       });

        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_searchScreen_to_searchByCategoryFragment);

            }
        });

       ingredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_searchScreen_to_searcByhIngridientFragment);

            }
        });
       return view;
    }

    @Override
    public void showMeals(List<MealsDetails> mealsDetails) {
        allMealsAdapter.setAllMealsItemList(mealsDetails);
        allMealsAdapter.notifyDataSetChanged();
    }
}