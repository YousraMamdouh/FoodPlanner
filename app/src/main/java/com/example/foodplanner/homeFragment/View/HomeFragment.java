package com.example.foodplanner.homeFragment.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.AccountFragment;
import com.example.foodplanner.R;
import com.example.foodplanner.calender.View.CalenderFragment;
import com.example.foodplanner.favorite.View.FavoriteFragment;
import com.example.foodplanner.search.View.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeFragment extends Fragment {

    BottomNavigationView navigationView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
      navigationView =  view.findViewById(R.id.bottom_navigation);
      navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              Fragment fragment = null;
              switch (item.getItemId()) {
//                  case R.id.nav_home:
//                      fragment = new HomeFragment();
//                      Navigation.findNavController(view).navigate(R.id.);
//                      break;

                  case R.id.nav_search:
                      fragment = new SearchFragment();
                      Navigation.findNavController(view).navigate(R.id.action_homeScreen_to_searchScreen);
                      break;

                  case R.id.nav_calendar:
                      fragment = new CalenderFragment();
                      Navigation.findNavController(view).navigate(R.id.action_homeScreen_to_calenderScreen);
                      break;

                  case R.id.nav_fav:
                      fragment = new FavoriteFragment();
                      Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeScreen);
                      break;


                  case R.id.nav_account:
                      fragment = new AccountFragment();
                      Navigation.findNavController(view).navigate(R.id.action_homeScreen_to_accountFragment);
                      break;

              }


              return true;
          }
      });
        // Inflate the layout for this fragment
        return view;
    }
}