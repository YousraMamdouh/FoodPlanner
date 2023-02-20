package com.example.foodplanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.authentication.View.AuthenticationFragment;
import com.example.foodplanner.utilities.ChangeNetworkListener;
import com.example.foodplanner.utilities.NetworkChecker;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public static NavController navController;

    ChangeNetworkListener changeNetworkListener = new ChangeNetworkListener();

//    @Override
//    protected void onStart() {
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(changeNetworkListener,filter);
//        super.onStart();
//    }

    @Override
    protected void onStop() {
        unregisterReceiver(changeNetworkListener);
        super.onStop();
    }

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
      //  getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,  new AuthenticationFragment()).addToBackStack(null).commit();
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
                    // getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,  new HomeFragment()).addToBackStack(null).commit();

                     break;
                    case R.id.nav_search:
                        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
                        registerReceiver(changeNetworkListener,filter);
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.searchScreen);
                      //  getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,  new SearchFragment()).addToBackStack(null).commit();



                        break;

                    case R.id.nav_weekPlan:
                        if (AuthenticationFragment.isAuthChecker())

                            Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.weekPlan);
                          else
                        showDialogue();
                   //     getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,  new Cal).addToBackStack(null).commit();


                        break;

                    case R.id.nav_fav:
                        if (AuthenticationFragment.isAuthChecker())
                        Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.favoriteFragment);
                        else
                         showDialogue();


                        break;


                    case R.id.nav_account:

                        if (AuthenticationFragment.isAuthChecker())

                            Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.accountFragment);
                        else
                            showDialogue();

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
//            case R.id.mealDetailsFragment:




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

    public void showDialogue()
    {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert")
                .setMessage("You can't use this feature \n unless you have an an account ")
                .setCancelable(true)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }


}