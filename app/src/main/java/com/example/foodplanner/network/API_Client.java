package com.example.foodplanner.network;

import com.example.foodplanner.model.RootMeals;
import com.example.foodplanner.searchByCategory.model.RootCategories;
import com.example.foodplanner.searchByCountry.model.RootCountries;
import com.example.foodplanner.searchByIngredient.model.RootIngredients;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Client implements  RemoteSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static final String Tag = "API CLIENT";
    private static API_Client client = null;

    API_Interface api_interface;
    API_Interface api_interface_meals;

    public static API_Client getInstance()
    { if(client==null)
    {
        client=new API_Client();
    }
        return client;
    }



    @Override
    public void enqueueCall(NetworkDelegate networkDelegate) {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.
                Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api_interface = retrofit.create(API_Interface.class);

        Observable<RootCategories> categoryObservable = api_interface.getAllMealsCategories();

        categoryObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            networkDelegate.onSuccessAllCategories(item.getCategoryItems());
            System.out.println("Categories are here");
        }, error -> {
            System.out.println("An error occurs while accessing the all categories API");
        }, () -> {
            System.out.println("Mission completed successfully");
        });

        Observable<RootMeals> allMealsObservable = api_interface.getAllMeals();

        allMealsObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            networkDelegate.onSuccessAllMeals(item.getAllMeals());
            System.out.println("Meals are here");
            System.out.println(item.getAllMeals());
        }, error -> {
            System.out.println("An error occurs while accessing the all categories API");
        }, () -> {
            System.out.println("Mission completed successfully");
        });


        Observable<RootCountries> countriesObservable=api_interface.getAllCountries();
        countriesObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            networkDelegate.onSuccessAllCountries(item.getCountries());
            System.out.println("countries are here");
            System.out.println(item.getCountries());
        }, error -> {
            System.out.println("An error occurs while accessing the all countries  API");
        }, () -> {
            System.out.println("Mission completed successfully");
        });

        Observable<RootIngredients> ingredientsObservable=api_interface.getAllIngredients();
        ingredientsObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            networkDelegate.onSuccessAllIngredients(item.getIngredients());
            System.out.println("Ingredients are here");
            System.out.println(item.getIngredients());
        }, error -> {
            System.out.println("An error occurs while accessing the all Ingredients  API");
        }, () -> {
            System.out.println("Mission completed successfully");
        });



    }

    @Override
    public void enqueueCallSpecificCategory(NetworkDelegate networkDelegate, String str) {
        System.out.println("gowa el call");
        Observable<RootMeals> mealsOfSelectedCountryObservable=api_interface.getMealsOfSelectedCategory(str);
mealsOfSelectedCountryObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
    networkDelegate.onSuccessSpecificCategory(item.getAllMeals());
    System.out.println("Specific category");
    System.out.println(item.getAllMeals());
}, error -> {
    System.out.println("An error occurs while accessing specific category");
}, () -> {
    System.out.println("Mission completed successfully");
});
    }

    @Override

    public void enqueueCallSpecificIngredient(NetworkDelegate networkDelegate, String str) {
        System.out.println("SpecificIngredient");
        Observable<RootMeals> mealsOfSelectedCountryObservable=api_interface.getMealsOfSelectedCategory(str);
        mealsOfSelectedCountryObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            networkDelegate.onSuccessSpecificIngredient(item.getAllMeals());
            System.out.println("Specific meal");
            System.out.println(item.getAllMeals());
        }, error -> {
            System.out.println("An error occurs while accessing specific category");
        });
    }

    public void enqueueCallMeal(NetworkDelegate networkDelegate, String mealName) {
        Observable<RootMeals> mealObservable=api_interface.getMeal(mealName);
        System.out.println("el meal eli 3awzaha:"+ mealName);
       mealObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
            networkDelegate.onSuccessMeal(item.getAllMeals());
            System.out.println("mstanya "+item.getAllMeals().get(0).getStrMeal());
            System.out.println(item.getAllMeals());
        }, error -> {
            System.out.println("An error while getting meal ");

        }, () -> {
            System.out.println("Mission completed successfully");
        });
    }

}
