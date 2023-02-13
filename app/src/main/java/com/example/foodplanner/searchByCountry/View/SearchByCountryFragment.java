package com.example.foodplanner.searchByCountry.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchByCountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchByCountryFragment extends Fragment implements CountriesViewInterface {
    RecyclerView countryRecyclerView;
    CountriesAdapter countriesAdapter;
    LinearLayoutManager layoutManager;
    CountriesPresenterInterface countriesPresenterInterface;

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
        View view= inflater.inflate(R.layout.fragment_search_by_country, container, false);
        countryRecyclerView=view.findViewById(R.id.countryRecyclerView);
        layoutManager=new LinearLayoutManager(getActivity());
        countriesAdapter=new CountriesAdapter(getActivity(), new ArrayList<>());
        countriesPresenterInterface= new CountriesPresenter(this,Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getActivity()),getActivity()));
       countryRecyclerView.setLayoutManager(layoutManager);
       countryRecyclerView.setAdapter(countriesAdapter);
       countriesPresenterInterface.getCountries();



        return view;

    }

    @Override
    public void showCountries(List<Countries> countries) {


        countriesAdapter.setCountriesItemsList(countries);
        countriesAdapter.notifyDataSetChanged();
    }
}