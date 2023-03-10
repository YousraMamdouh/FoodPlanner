package com.example.foodplanner.weekPlan.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.dataBase.ConcreteLocalSource;

import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.weekPlan.presenter.WeekPlanPresenter;
import com.example.foodplanner.weekPlan.presenter.WeekPlanPresenterInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeekPlan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekPlan extends Fragment implements OnMealClickedListener, WeekPlanViewInterface {

    private static final String TAG = "Week Plan";
    private boolean showSatMeals=false;
    private boolean showSunMeals=false;
    private boolean showMonMeals=false;
    private boolean showTuesMeals=false;
    private boolean showWedMeals=false;
    private boolean showThuMeals=false;
    private boolean showFriMeals=false;
    private TextView saturdayView,sundayView,mondayView,tuesdayView,wednesdayView,thursdayView,fridayView;
    private RecyclerView saturdayRecyclerView, sundayRecyclerView, mondayRecyclerView, tuesdayRecyclerView, wednesdayRecyclerView, thursdayRecyclerView, fridayRecyclerView;
    private WeekPlanAdapter wednesdayAdapter, saturdayAdapter, sundayAdapter, mondayAdapter, tuesdayAdapter, thursdayAdapter, fridayAdapter;
    private List<MealsDetails> allMeals;
    private String day;
    LinearLayoutManager layoutManager1,layoutManager2,layoutManager3,layoutManager4,layoutManager5,layoutManager6,layoutManager7;
    private WeekPlanPresenterInterface weekPlanPresenterInterface;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeekPlan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment weekPlan.
     */
    // TODO: Rename and change types and number of parameters
    public static WeekPlan newInstance(String param1, String param2) {
        WeekPlan fragment = new WeekPlan();
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
View view= inflater.inflate(R.layout.fragment_week_plan, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init(view);
        saturdayRecyclerView.setVisibility(view.GONE);
        sundayRecyclerView.setVisibility(view.GONE);
        mondayRecyclerView.setVisibility(view.GONE);
        tuesdayRecyclerView.setVisibility(view.GONE);
        wednesdayRecyclerView.setVisibility(view.GONE);
        thursdayRecyclerView.setVisibility(view.GONE);
        fridayRecyclerView.setVisibility(view.GONE);

        saturdayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showSatMeals){
                saturdayRecyclerView.setVisibility(view.VISIBLE);
                showSatMeals=true;
                }
                else {
                    saturdayRecyclerView.setVisibility(view.GONE);
                    showSatMeals=false;
                }

            }
        });
        sundayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!showSunMeals){
                    sundayRecyclerView.setVisibility(view.VISIBLE);
                    showSunMeals=true;
                }
                else {
                    sundayRecyclerView.setVisibility(view.GONE);
                    showSunMeals=false;
                }
            }
        });
       mondayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showMonMeals) {
                    mondayRecyclerView.setVisibility(view.VISIBLE);
                    showMonMeals=true;
                }
                else {
                    mondayRecyclerView.setVisibility(view.GONE);
                    showMonMeals=false;
                }
            }
        });
       tuesdayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showTuesMeals) {
                    tuesdayRecyclerView.setVisibility(view.VISIBLE);
                    showTuesMeals=true;
                }
                else {
                  tuesdayRecyclerView.setVisibility(view.GONE);
                    showTuesMeals=false;
                }
            }
        });
     wednesdayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showWedMeals) {
                    wednesdayRecyclerView.setVisibility(view.VISIBLE);
                    showWedMeals=true;
                }
                else {
                   wednesdayRecyclerView.setVisibility(view.GONE);
                    showWedMeals=false;
                }
            }
        });
     thursdayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showThuMeals) {
                   thursdayRecyclerView.setVisibility(view.VISIBLE);
                    showThuMeals=true;
                }
                else {
                   thursdayRecyclerView.setVisibility(view.GONE);
                    showThuMeals=false;
                }
            }
        });
     fridayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showFriMeals) {
                   fridayRecyclerView.setVisibility(view.VISIBLE);
                    showFriMeals=true;
                }
                else {
                    fridayRecyclerView.setVisibility(view.GONE);
                    showFriMeals=false;
                }
            }
        });

      weekPlanPresenterInterface= new WeekPlanPresenter(this, Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getActivity()),getActivity()));
        if( weekPlanPresenterInterface == null){
            System.out.println("ana null");
        }
     else {
            System.out.println("msh null");
        }

        allMeals = new ArrayList<>();
        day = null;
        saturdayAdapter = new WeekPlanAdapter(getContext(), WeekPlan.this);
        sundayAdapter = new WeekPlanAdapter(getContext(), WeekPlan.this);
        mondayAdapter = new WeekPlanAdapter(getContext(), WeekPlan.this);
        tuesdayAdapter = new WeekPlanAdapter(getContext(), WeekPlan.this);
        thursdayAdapter = new WeekPlanAdapter(getContext(), WeekPlan.this);
        wednesdayAdapter = new WeekPlanAdapter(getContext(), WeekPlan.this);
        fridayAdapter = new WeekPlanAdapter(getContext(), WeekPlan.this);
        layoutManager1 = new LinearLayoutManager(getActivity());
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        saturdayRecyclerView.setLayoutManager(layoutManager1);
        layoutManager2 = new LinearLayoutManager(getActivity());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        sundayRecyclerView.setLayoutManager(layoutManager2);
        layoutManager3 = new LinearLayoutManager(getActivity());
        layoutManager3.setOrientation(RecyclerView.HORIZONTAL);
        mondayRecyclerView.setLayoutManager(layoutManager3);
        layoutManager4 = new LinearLayoutManager(getActivity());
        layoutManager4.setOrientation(RecyclerView.HORIZONTAL);
        tuesdayRecyclerView.setLayoutManager(layoutManager4);
        layoutManager5 = new LinearLayoutManager(getActivity());
        layoutManager5.setOrientation(RecyclerView.HORIZONTAL);
        wednesdayRecyclerView.setLayoutManager(layoutManager5);
        layoutManager6 = new LinearLayoutManager(getActivity());
        layoutManager6.setOrientation(RecyclerView.HORIZONTAL);
        thursdayRecyclerView.setLayoutManager(layoutManager6);
        layoutManager7 =new LinearLayoutManager(getActivity());
        layoutManager7.setOrientation(RecyclerView.HORIZONTAL);
        fridayRecyclerView.setLayoutManager(layoutManager7);


        weekPlanPresenterInterface.getMyPlannedMeals("saturday").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<MealsDetails>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MealsDetails> mealsDetails) {
                saturdayAdapter.setAllMeals(mealsDetails);
                saturdayRecyclerView.setAdapter(saturdayAdapter);
                saturdayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error sat"+e);

            }

            @Override
            public void onComplete() {
                System.out.println("complete sat");

            }

        });
        weekPlanPresenterInterface.getMyPlannedMeals("sunday").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<MealsDetails>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MealsDetails> mealsDetails) {
                sundayAdapter.setAllMeals(mealsDetails);

                sundayRecyclerView.setAdapter(sundayAdapter);
                sundayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

        });
        weekPlanPresenterInterface.getMyPlannedMeals("monday").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<MealsDetails>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MealsDetails> mealsDetails) {
                mondayAdapter.setAllMeals(mealsDetails);
                mondayRecyclerView.setAdapter(mondayAdapter);
               mondayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

        });

        weekPlanPresenterInterface.getMyPlannedMeals("tuesday").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<MealsDetails>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MealsDetails> mealsDetails) {
                tuesdayAdapter.setAllMeals(mealsDetails);
                tuesdayRecyclerView.setAdapter(tuesdayAdapter);
                tuesdayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

        });
        weekPlanPresenterInterface.getMyPlannedMeals("wednesday").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<MealsDetails>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MealsDetails> mealsDetails) {
               wednesdayAdapter.setAllMeals(mealsDetails);
                wednesdayRecyclerView.setAdapter(wednesdayAdapter);
                wednesdayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

        });
        weekPlanPresenterInterface.getMyPlannedMeals("thursday").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<MealsDetails>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MealsDetails> mealsDetails) {
              thursdayAdapter.setAllMeals(mealsDetails);
                thursdayRecyclerView.setAdapter(thursdayAdapter);
                thursdayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

        });
        weekPlanPresenterInterface.getMyPlannedMeals("friday").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<MealsDetails>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<MealsDetails> mealsDetails) {
               fridayAdapter.setAllMeals(mealsDetails);
                fridayRecyclerView.setAdapter(fridayAdapter);
                fridayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

        });
        saturdayAdapter.setOnMealClickedListener(this);
        sundayAdapter.setOnMealClickedListener(this);
        mondayAdapter.setOnMealClickedListener(this);
        tuesdayAdapter.setOnMealClickedListener(this);
        wednesdayAdapter.setOnMealClickedListener(this);
        thursdayAdapter.setOnMealClickedListener(this);
       fridayAdapter.setOnMealClickedListener(this);
    }

    @Override
    public void deleteMealFromDay(MealsDetails mealsDetails) {
        System.out.println("delete now");
        weekPlanPresenterInterface.removeMealFromPlannedMeal(mealsDetails);
        saturdayAdapter.notifyDataSetChanged();
        sundayAdapter.notifyDataSetChanged();
        mondayAdapter.notifyDataSetChanged();
        tuesdayAdapter.notifyDataSetChanged();
        wednesdayAdapter.notifyDataSetChanged();
        thursdayAdapter.notifyDataSetChanged();
        fridayAdapter.notifyDataSetChanged();
    }


    @Override
    public void showMeals(List<MealsDetails> mealsDetails) {
        if (mealsDetails != null) {
            allMeals.clear();
            for (MealsDetails meal : mealsDetails)
                allMeals.add(meal);
        }
    }

    public void init(View view) {
        saturdayRecyclerView = view.findViewById(R.id.recyclerViewsat);
        sundayRecyclerView = view.findViewById(R.id.recyclerViewSun);
        mondayRecyclerView = view.findViewById(R.id.recyclerViewMon);
        tuesdayRecyclerView = view.findViewById(R.id.recyclerViewTuesday);
        wednesdayRecyclerView = view.findViewById(R.id.recyclerViewWed);
        thursdayRecyclerView = view.findViewById(R.id.recyclerViewThurs);
        fridayRecyclerView = view.findViewById(R.id.recyclerViewFri);
        saturdayView=view.findViewById(R.id.satTxt);
        sundayView=view.findViewById(R.id.sunTxt);
        mondayView=view.findViewById(R.id.monTxt);
        tuesdayView=view.findViewById(R.id.tuesTxt);
        wednesdayView=view.findViewById(R.id.wedTxt);
        thursdayView=view.findViewById(R.id.ThuTxt);
        fridayView=view.findViewById(R.id.FriTxt);

     }

}