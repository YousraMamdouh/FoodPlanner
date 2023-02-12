package com.example.foodplanner.network;

import com.example.foodplanner.model.RootCategories;
import com.example.foodplanner.model.RootMeals;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Client implements RemoteSource{
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static final String Tag = "API CLIENT";
    private static API_Client client = null;

    API_Interface api_interface;

    public static API_Client getInstance()
    { if(client==null)
    {
        client=new API_Client();
    }
        return client;
    }

    public void enqueueCall(NetworkDelegate networkDelegate) {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.
                Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api_interface = retrofit.create(API_Interface.class);
        Observable<RootCategories> observable = api_interface.getAllMealsCategories();
        System.out.println("Api");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            networkDelegate.onSuccessResult(item.getCategoryItems());
            System.out.println("My items: "+item.getCategoryItems());
           System.out.println("My items' size: "+item.getCategoryItems().size());
        }, error -> {
            System.out.println("An error occurs while accessing the API");
        }, () -> {
            System.out.println("Mission completed successfully");
        });



    }
}
