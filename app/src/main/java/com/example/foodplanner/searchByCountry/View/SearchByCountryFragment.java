package com.example.foodplanner.searchByCountry.View;

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
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByCountry.presenter.CountriesPresenter;
import com.example.foodplanner.searchByCountry.presenter.CountriesPresenterInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchByCountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchByCountryFragment extends Fragment implements CountriesViewInterface, GetMealsClickListener {
    RecyclerView countryRecyclerView;
    View view;
    CountriesAdapter countriesAdapter;
    LinearLayoutManager layoutManager;
    CountriesPresenterInterface countriesPresenterInterface;
    SearchView searchView;
    List<Countries> countries;
    List<Countries> filteredList;
    List<Integer> countriesPictures=List.of(
            R.drawable.america,R.drawable.british,R.drawable.canada,R.drawable.china,
            R.drawable.croatian,R.drawable.dutch,R.drawable.egypt,R.drawable.french,
            R.drawable.greek,R.drawable.indian,R.drawable.irish,
            R.drawable.italian,R.drawable.jamaican,R.drawable.japan,R.drawable.kenya,
            R.drawable.malaysian,R.drawable.mexico,R.drawable.moroco,R.drawable.polish,
            R.drawable.portug,R.drawable.russian,R.drawable.spani, R.drawable.thia,
            R.drawable.tunisian,R.drawable.turcia,R.drawable.unknown,R.drawable.vietnam


    );

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchByCountryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchByCountryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchByCountryFragment newInstance(String param1, String param2) {
        SearchByCountryFragment fragment = new SearchByCountryFragment();
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
        view= inflater.inflate(R.layout.fragment_search_by_country, container, false);
        countryRecyclerView=view.findViewById(R.id.categoryRecyclerView);
        searchView=view.findViewById(R.id.searchView);
        layoutManager=new LinearLayoutManager(getActivity());

        countriesAdapter=new CountriesAdapter(getActivity(), new ArrayList<>(),this);
        countriesPresenterInterface= new CountriesPresenter(this,Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getActivity()),getActivity()));
       countryRecyclerView.setLayoutManager(layoutManager);
       countryRecyclerView.setAdapter(countriesAdapter);
       countriesPresenterInterface.getCountries();
        filterCountries();




        return view;

    }

    @Override
    public void showCountries(List<Countries> countries) {
        this.countries=countries;
        countriesAdapter.setCountriesItemsList(countries);
//        countriesAdapter.setCountriesPictures(countriesPictures);

        countriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMealsOfClickedCategory(String cuisineName) {
        com.example.foodplanner.searchByCountry.View.SearchByCountryFragmentDirections.ActionSearchByCountryFragmentToSpecificCuisine action=
                SearchByCountryFragmentDirections.actionSearchByCountryFragmentToSpecificCuisine(cuisineName);
        Navigation.findNavController(view).navigate(action);

    }
    public void filterCountries()
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

                filteredList=countries.stream().filter(r->r.getStrArea().toLowerCase().contains(newText.toLowerCase())).collect(Collectors.toList());
                countriesAdapter.setCountriesItemsList(filteredList);
                countriesAdapter.notifyDataSetChanged();

                return false;
            }
        })).doOnNext(c -> Log.i(Tag, "UpSteam: " + c)).subscribe(r ->
                Log.i(Tag, "DownStream: " + r));


    }
}