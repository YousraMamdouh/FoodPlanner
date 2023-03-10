package com.example.foodplanner.splashScreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SplashScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashScreenFragment extends Fragment {


    LottieAnimationView chefAnimation;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SplashScreenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SplashScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static SplashScreenFragment newInstance(String param1, String param2) {
        SplashScreenFragment fragment = new SplashScreenFragment();
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
        View view=inflater.inflate(R.layout.fragment_splash_screen, container, false);
        chefAnimation=view.findViewById(R.id.chefAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences preferences= getActivity().getSharedPreferences("PREFRENCE", Context.MODE_PRIVATE);
                String firstTime=preferences.getString("FirstTimeInstall","");
                if(firstTime.equals("Yes")) {

                    Navigation.findNavController(view).navigate(R.id.action_splashScreen_to_authentication);

                }
                else {

                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("FirstTimeInstall","Yes");
                    editor.apply();
                    Navigation.findNavController(view).navigate(R.id.action_splashScreen_to_blankFragmentOnBoarding);

                }

            }
        },3000);
        return view;
    }
}