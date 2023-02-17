package com.example.foodplanner.search.View;

import static com.example.foodplanner.network.API_Client.Tag;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
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
import com.example.foodplanner.search.presentor.AllMealsPresenterInterface;
import com.example.foodplanner.search.presentor.allMealsPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements AllMealsViewInterface , AddToFavoriteClickListener, OnMealClickListener {
Button cuisineButton;
    View view;
Button categoryButton;
Button ingredientButton;
RecyclerView allMealsRecyclerView;
AllMealsAdapter allMealsAdapter;
StaggeredGridLayoutManager layoutManager;
SearchView searchView;
List<MealsDetails> mealList;
List<MealsDetails> filteredMealsList;
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
       view=inflater.inflate(R.layout.fragment_search_screen, container, false);
searchView=view.findViewById(R.id.searchView);
       cuisineButton=view.findViewById(R.id.cuisineButton);
       categoryButton=view.findViewById(R.id.categoryButton);
       ingredientButton=view.findViewById(R.id.ingredientsButton);
       allMealsRecyclerView=view.findViewById(R.id.allMealsRecyclerView);
    //   layoutManager=new LinearLayoutManager(getActivity());
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
       allMealsAdapter=new AllMealsAdapter(getActivity(),new ArrayList<>(),this,this);
       allMealsPresenterInterface=new allMealsPresenter(this,Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getActivity()),getActivity()));
       allMealsRecyclerView.setLayoutManager(layoutManager);
       allMealsRecyclerView.setAdapter(allMealsAdapter);
       allMealsPresenterInterface.getMeals();
        filterMeals();

       cuisineButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_searchScreen_to_searchByCountryFragment));

        categoryButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_searchScreen_to_searchByCategoryFragment));

       ingredientButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_searchScreen_to_searcByhIngridientFragment));
       return view;
    }

    @Override
    public void showMeals(List<MealsDetails> mealsDetails) {
        mealList=mealsDetails;
        allMealsAdapter.setAllMealsItemList(mealsDetails);
        allMealsAdapter.notifyDataSetChanged();

    }

    @Override
    public void addMealToFavorites(MealsDetails mealsDetails) {
allMealsPresenterInterface.addToFavorites(mealsDetails);
    }

    @Override
    public void onClick(MealsDetails currentMeal) {
        Toast.makeText(getActivity(), "Meal added to favorites", Toast.LENGTH_SHORT).show();
addMealToFavorites(currentMeal);
    }

    @Override
    public void getMeal(String mealName) {
       com.example.foodplanner.search.View.SearchFragmentDirections.ActionSearchScreenToMealDetailsFragment action=SearchFragmentDirections.actionSearchScreenToMealDetailsFragment(mealName);
        Navigation.findNavController(view).navigate(action);

    }

    public void filterMeals()
    {
        Observable.create(emitter -> searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                if(newText.length()!=0)

                    emitter.onNext(newText);
                    filteredMealsList=mealList.stream().filter(r->r.getStrMeal().toLowerCase().contains(newText.toLowerCase())).collect(Collectors.toList());
                    allMealsAdapter.setAllMealsItemList(filteredMealsList);
                    allMealsAdapter.notifyDataSetChanged();

                return false;
            }
        })).doOnNext(c -> Log.i(Tag, "UpSteam: " + c)).subscribe(r ->
                Log.i(Tag, "DownStream: " + r));

    }
}