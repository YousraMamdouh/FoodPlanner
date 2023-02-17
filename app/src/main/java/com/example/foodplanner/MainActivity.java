package com.example.foodplanner;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public static NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
      //  getSupportFragmentManager().beginTransaction().replace(R.id.nav_graphContainer,  new AuthenticationFragment()).commit();
        //navigationView.setSelectedItemId(R.id.nav_home);
        navController=Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        //  getWindow().addFlags(window);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                 case R.id.nav_home:
                     Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.homeScreen);

                     break;

                    case R.id.nav_search:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.searchScreen);


                        break;

                    case R.id.nav_calendar:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.calenderScreen);


                        break;

                    case R.id.nav_fav:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.favoriteFragment);


                        break;


                    case R.id.nav_account:
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.accountFragment);


                        break;

                }
//getSupportFragmentManager().beginTransaction().replace(R.id.nav_graphContainer,fragment).commit();

                return true;
            }
        });
navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
    @Override
    public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
        switch (navDestination.getId()) {
            case R.id.splashScreen:
            case R.id.authentication:
            case R.id.loginFragment:
            case R.id.signUpFragment:
            case R.id.blankFragmentOnBoarding:




                bottomNavigationView.setVisibility(View.GONE);
              //  drawerButton.setVisibility(View.VISIBLE);


                break;
            default:
                bottomNavigationView.setVisibility(View.VISIBLE);
              //  drawerButton.setVisibility(View.GONE);

        }
    }
});

    }


}