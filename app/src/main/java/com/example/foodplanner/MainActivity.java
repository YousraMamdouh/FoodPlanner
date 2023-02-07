package com.example.foodplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.foodplanner.calender.View.CalenderFragment;
import com.example.foodplanner.favorite.View.FavoriteFragment;
import com.example.foodplanner.homeFragment.View.HomeFragment;
import com.example.foodplanner.search.View.SearchFragment;
import com.example.foodplanner.userDetails.AccountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_graphContainer,new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);
   //  getWindow().addFlags(window);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                 case R.id.nav_home:
                     fragment = new HomeFragment();

                     break;

                    case R.id.nav_search:
                        fragment = new SearchFragment();

                        break;

                    case R.id.nav_calendar:
                        fragment = new CalenderFragment();

                        break;

                    case R.id.nav_fav:
                        fragment = new FavoriteFragment();

                        break;


                    case R.id.nav_account:
                        fragment = new AccountFragment();

                        break;

                }
getSupportFragmentManager().beginTransaction().replace(R.id.nav_graphContainer,fragment).commit();

                return true;
            }
        });


    }
}