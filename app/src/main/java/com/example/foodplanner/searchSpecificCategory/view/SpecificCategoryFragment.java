package com.example.foodplanner.searchSpecificCategory.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.foodplanner.R;
import com.example.foodplanner.dataBase.ConcreteLocalSource;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.searchSpecificCategory.presenter.SpecificCategoryPresenter;
import com.example.foodplanner.searchSpecificCategory.presenter.SpecificCategoryPresenterInterface;
import com.example.foodplanner.searchSpecificCategory.view.SpecificCategoryFragmentDirections.ActionSearchSpecificCategoryToMealDetailsFragment;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpecificCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpecificCategoryFragment extends Fragment implements SpecificCategoryViewInterface,AddToFavorites,OnMealClickedListener {

    RecyclerView recyclerView;
   SpecificCategoryAdapter adapter;
    StaggeredGridLayoutManager layoutManager;
    SpecificCategoryPresenterInterface specificCategoryPresenterInterface;
    View view;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SpecificCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment searchSpecificCategory.
     */
    // TODO: Rename and change types and number of parameters
    public static SpecificCategoryFragment newInstance(String param1, String param2) {
        SpecificCategoryFragment fragment = new SpecificCategoryFragment();
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
       view= inflater.inflate(R.layout.fragment_search_specific_category, container, false);
       recyclerView=view.findViewById(R.id.categoryRecyclerView);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter=new SpecificCategoryAdapter(getActivity(),new ArrayList<>(),this,this);
        specificCategoryPresenterInterface=new SpecificCategoryPresenter(this, Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getActivity()),getActivity()),SpecificCategoryFragmentArgs.fromBundle(getArguments()).getCategoryName());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        specificCategoryPresenterInterface.getMeals();
        return view;

    }

    @Override
    public void showMeals(List<MealsDetails> mealsDetails) {
        adapter.setCategoryItemList(mealsDetails);
        System.out.println("meals:"+mealsDetails.size());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void addMealToFavorites(MealsDetails mealsDetails) {
        specificCategoryPresenterInterface.addToFavorites(mealsDetails);
        Toast.makeText(getActivity(), "yousra", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(MealsDetails currentMeal) {
        Toast.makeText(getActivity(), "Meal added to favorites", Toast.LENGTH_SHORT).show();
        addMealToFavorites(currentMeal);
    }

    @Override
    public void getMeal(String mealName) {
ActionSearchSpecificCategoryToMealDetailsFragment action = SpecificCategoryFragmentDirections.actionSearchSpecificCategoryToMealDetailsFragment(mealName);
        Navigation.findNavController(view).navigate(action);

    }
}