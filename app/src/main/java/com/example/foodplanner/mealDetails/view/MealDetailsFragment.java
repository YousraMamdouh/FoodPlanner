package com.example.foodplanner.mealDetails.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.dataBase.ConcreteLocalSource;
import com.example.foodplanner.mealDetails.presenter.MealPresenter;
import com.example.foodplanner.mealDetails.presenter.MealPresenterInterface;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.searchByIngredient.model.Ingredients;
import com.example.foodplanner.specificIngredient.view.SingleChoiceDialogFragment;
import com.example.foodplanner.specificIngredient.view.SpecificIngredient;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MealDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealDetailsFragment extends Fragment implements AddToFavorites,MealViewInterface,SingleChoiceDialogFragment.SingleChoiceListener {

    RecyclerView recyclerView;
    MealAdapter adapter;
   LinearLayoutManager layoutManager;
   MealPresenterInterface mealPresenterInterface;
   ImageView mealImage;
   TextView mealName;
   TextView recipeView;
   TextView mealCountry;
    YouTubePlayerView youTubePlayerView;
   Button addToFavButton;
   View view;
   Button calender;
 MealsDetails mealObject;
List<String> ingredientsList=new ArrayList<>();
    private AddToFavorites addToFavorites=this;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MealDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MealDetailsFragment newInstance(String param1, String param2) {
        MealDetailsFragment fragment = new MealDetailsFragment();
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
       view= inflater.inflate(R.layout.fragment_meal_details, container, false);
       mealName=view.findViewById(R.id.myName);
       mealImage=view.findViewById(R.id.myImage);
        calender=view.findViewById(R.id.calenderbtn);


        youTubePlayerView =view.findViewById(R.id.videoView);
       recipeView=view.findViewById(R.id.recipeView);
       recyclerView=view.findViewById(R.id.myRecyclerView);
       addToFavButton=view.findViewById(R.id.addToFavButton);
       mealCountry=view.findViewById(R.id.myCountry);
       layoutManager=new LinearLayoutManager(getActivity());
       adapter=new MealAdapter(getActivity(),new ArrayList<>());
       mealPresenterInterface=new MealPresenter(this, Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getActivity()),getActivity()),MealDetailsFragmentArgs.fromBundle(getArguments()).getMealName());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setAdapter(adapter);
        mealPresenterInterface.getMeal();
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment singleChoiceDialog = new SingleChoiceDialogFragment();
                singleChoiceDialog.setCancelable(false);
                singleChoiceDialog.show(getParentFragmentManager(),"Single Choice Dialog");
                singleChoiceDialog.setTargetFragment(MealDetailsFragment.this,1);
            }
        });

        addToFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavorites.onClick(mealObject);
            }
        });


       return  view;
    }

    @Override
    public void onClick(MealsDetails currentMeal) {
        Toast.makeText(getActivity(), "Meal added to favorites", Toast.LENGTH_SHORT).show();
        addMealToFavorites(currentMeal);
    }





    @Override
    public void showIngredients(List<Ingredients> ingredients) {


//        adapter.setIngredientsList(ingredientsList);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMealDetails(MealsDetails mealsDetails) {
        System.out.println("hi");
       mealObject=mealsDetails;
        mealName.setText(mealObject.getStrMeal());
        recipeView.setText(mealObject.getStrInstructions());
        mealCountry.setText(mealObject.getStrArea());
        adapter.setIngredientsList(getExistIngredients());
        adapter.notifyDataSetChanged();


        getLifecycle().addObserver(youTubePlayerView);
     youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                final String[] videoUrl = {mealObject.getStrYoutube()};

                if(videoUrl[0] !=null&&!videoUrl[0].equals("")){
                    videoUrl[0] = videoUrl[0].substring(videoUrl[0].indexOf("=") + 1);
                    StringTokenizer str = new StringTokenizer(videoUrl[0], "&");
                    videoUrl[0] = str.nextToken();
                    youTubePlayer.loadVideo(videoUrl[0],0);
                    youTubePlayer.pause();
                }
            }
        });

        Glide.with(getActivity()).load(mealObject.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(150,150)).into(mealImage);

//
//adapter.notifyDataSetChanged();


    }


    @Override
    public void addMealToFavorites(MealsDetails mealsDetails) {
        mealPresenterInterface.addToFavorites(mealsDetails);
    }

    public List<String> getExistIngredients()
    {
        if(  mealObject.getStrIngredient1()!=null&& !mealObject.getStrIngredient1().equals(""))
            ingredientsList.add(mealObject.getStrIngredient1());
        if(  mealObject.getStrIngredient2()!=null &&  !mealObject.getStrIngredient2().equals(""))
            ingredientsList.add(mealObject.getStrIngredient2());
        if(  mealObject.getStrIngredient3()!=null && ! mealObject.getStrIngredient3().equals(""))
            ingredientsList.add(mealObject.getStrIngredient3());
        if(  mealObject.getStrIngredient4()!=null &&  !mealObject.getStrIngredient4().equals(""))
            ingredientsList.add(mealObject.getStrIngredient4());
        if(  mealObject.getStrIngredient5()!=null &&  !mealObject.getStrIngredient5().equals(""))
            ingredientsList.add(mealObject.getStrIngredient5());
        if(  mealObject.getStrIngredient6()!=null &&  !mealObject.getStrIngredient6().equals(""))
            ingredientsList.add(mealObject.getStrIngredient6());
        if(  mealObject.getStrIngredient7()!=null &&  !mealObject.getStrIngredient7().equals(""))
            ingredientsList.add(mealObject.getStrIngredient7());
        if(  mealObject.getStrIngredient8()!=null &&  !mealObject.getStrIngredient8().equals(""))
            ingredientsList.add(mealObject.getStrIngredient8());
        if(  mealObject.getStrIngredient9()!=null &&  !mealObject.getStrIngredient9().equals(""))
            ingredientsList.add(mealObject.getStrIngredient9());
        if(  mealObject.getStrIngredient10()!=null &&  !mealObject.getStrIngredient10().equals(""))
            ingredientsList.add(mealObject.getStrIngredient10());
        if(  mealObject.getStrIngredient11()!=null &&  !mealObject.getStrIngredient11().equals(""))
            ingredientsList.add(mealObject.getStrIngredient11());
        if(  mealObject.getStrIngredient12()!=null &&  !mealObject.getStrIngredient12().equals(""))
            ingredientsList.add(mealObject.getStrIngredient12());
        if(  mealObject.getStrIngredient13()!=null &&  !mealObject.getStrIngredient13().equals(""))
            ingredientsList.add(mealObject.getStrIngredient13());
        if(  mealObject.getStrIngredient14()!=null &&  !mealObject.getStrIngredient14().equals(""))
            ingredientsList.add(mealObject.getStrIngredient14());
        if(  mealObject.getStrIngredient15()!=null &&  !mealObject.getStrIngredient15().equals(""))
            ingredientsList.add(mealObject.getStrIngredient15());
        if(  mealObject.getStrIngredient16()!=null &&  !mealObject.getStrIngredient16().equals(""))
            ingredientsList.add(mealObject.getStrIngredient16());
        if(  mealObject.getStrIngredient17()!=null &&  !mealObject.getStrIngredient17().equals(""))
            ingredientsList.add(mealObject.getStrIngredient17());
        if(  mealObject.getStrIngredient18()!=null &&  !mealObject.getStrIngredient18().equals(""))
            ingredientsList.add(mealObject.getStrIngredient18());
        if(  mealObject.getStrIngredient19()!=null &&  !mealObject.getStrIngredient19().equals(""))
            ingredientsList.add(mealObject.getStrIngredient19());
        if(  mealObject.getStrIngredient20()!=null &&  !mealObject.getStrIngredient20().equals(""))
            ingredientsList.add(mealObject.getStrIngredient20());

return ingredientsList;
    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        System.out.println(list[position]+"ffff");
       mealPresenterInterface.addToCalender(mealObject,list[position]);
    }

    @Override
    public void onNegativeButtonClicked() {

    }
}