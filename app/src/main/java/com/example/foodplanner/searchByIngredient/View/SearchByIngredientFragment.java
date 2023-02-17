package com.example.foodplanner.searchByIngredient.View;

import static com.example.foodplanner.network.API_Client.Tag;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.dataBase.ConcreteLocalSource;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.searchByCategory.View.GetMealsClickListener;
import com.example.foodplanner.searchByIngredient.model.Ingredients;
import com.example.foodplanner.searchByIngredient.presenter.IngredientsPresenter;
import com.example.foodplanner.searchByIngredient.presenter.IngredientsPresenterInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchByIngredientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchByIngredientFragment extends Fragment implements IngredientsViewInterface, GetMealsClickListener {
    RecyclerView ingredientRecyclerView;
   IngredientsAdapter ingredientsAdapter;
    LinearLayoutManager layoutManager;
    IngredientsPresenterInterface ingredientsPresenterInterface;
    View view;

    SearchView searchView;
    List<Ingredients> ingredients;
    List<Ingredients> filteredList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchByIngredientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearcByhIngridientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchByIngredientFragment newInstance(String param1, String param2) {
        SearchByIngredientFragment fragment = new SearchByIngredientFragment();
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
        view= inflater.inflate(R.layout.fragment_search_by_ingridient, container, false);
        ingredientRecyclerView=view.findViewById(R.id.categoryRecyclerView);
        searchView=view.findViewById(R.id.searchView);
        layoutManager=new LinearLayoutManager(getActivity());
        ingredientsAdapter=new IngredientsAdapter(getActivity(),new ArrayList<>(),this);
        ingredientsPresenterInterface= new IngredientsPresenter(this,Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getActivity()),getActivity()));
        ingredientRecyclerView.setLayoutManager(layoutManager);
        ingredientRecyclerView.setAdapter(ingredientsAdapter);
        ingredientsPresenterInterface.getIngredients();
        filterIngredients();



        return  view;
    }

    @Override
    public void showIngredients(List<Ingredients> ingredients) {
       this.ingredients=ingredients;
        ingredientsAdapter.setIngredientsItemsList(ingredients);
        ingredientsAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMealsOfClickedCategory(String IngredientName) {
        com.example.foodplanner.searchByIngredient.View.SearchByIngredientFragmentDirections.ActionSearcByhIngridientFragmentToSpecificIngredient action=
               SearchByIngredientFragmentDirections.actionSearcByhIngridientFragmentToSpecificIngredient(IngredientName);
        Navigation.findNavController(view).navigate(action);
    }
    public void filterIngredients()
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
                filteredList=ingredients.stream().filter(r->r.getStrIngredient().toLowerCase().contains(newText.toLowerCase())).collect(Collectors.toList());
                ingredientsAdapter.setIngredientsItemsList(filteredList);
                ingredientsAdapter.notifyDataSetChanged();

                return false;
            }
        })).doOnNext(c -> Log.i(Tag, "UpSteam: " + c)).subscribe(r ->
                Log.i(Tag, "DownStream: " + r));


    }
}