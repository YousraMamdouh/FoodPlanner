package com.example.foodplanner.network;

import com.example.foodplanner.model.RootMeals;
import com.example.foodplanner.search.presentor.NetworkDelegateForAllMeals;
import com.example.foodplanner.search.presentor.RemoteSourceForAllMeals;
import com.example.foodplanner.searchByCategory.model.RootCategories;
import com.example.foodplanner.searchByCategory.presenter.NetworkDelegateForAllCategories;
import com.example.foodplanner.searchByCategory.presenter.RemoteSourceForAllCategories;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Client implements RemoteSourceForAllCategories, RemoteSourceForAllMeals {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static final String Tag = "API CLIENT";
    private static API_Client client = null;

    API_Interface api_interface_categories;
    API_Interface api_interface_meals;

    public static API_Client getInstance()
    { if(client==null)
    {
        client=new API_Client();
    }
        return client;
    }

    public void enqueueCallCategories(NetworkDelegateForAllCategories networkDelegateForAllCategories) {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.
                Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api_interface_categories = retrofit.create(API_Interface.class);
        //Categories observable
        Observable<RootCategories> categoryObservable = api_interface_categories.getAllMealsCategories();

        categoryObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            networkDelegateForAllCategories.onSuccessAllCategories(item.getCategoryItems());
            System.out.println("Categories are here");
        }, error -> {
            System.out.println("An error occurs while accessing the all categories API");
        }, () -> {
            System.out.println("Mission completed successfully");
        });


    }

    //        All meals observable
    @Override
    public void enqueueCallMeals(NetworkDelegateForAllMeals networkDelegateForAllMeals) {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.
                Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api_interface_meals = retrofit.create(API_Interface.class);


        Observable<RootMeals> allMealsObservable = api_interface_meals.getAllMeals();

        allMealsObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            networkDelegateForAllMeals.onSuccessAllMeals(item.getAllMeals());
            System.out.println("Meals are here");
        }, error -> {
            System.out.println("An error occurs while accessing the all categories API");
        }, () -> {
            System.out.println("Mission completed successfully");
        });
    }
}
