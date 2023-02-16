package com.example.foodplanner.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.dataBase.LocalSource;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.network.RemoteSource;

import java.util.List;

public class Repository implements RepositoryInterface{

    private Context context;
    private RemoteSource remoteSource;
    private LocalSource localSource;
    private static Repository repository=null;


    private Repository(RemoteSource remoteSource,LocalSource localSource , Context context)
    {
        this.context=context;
        this.remoteSource = remoteSource;
        this.localSource=localSource;
      //  this.localSource=localSource;

    }

    public static Repository getInstance(RemoteSource remoteSource, LocalSource localSource,Context context){

        if(repository==null)
        {
            repository=new Repository(remoteSource, localSource, context);
        }
        return repository;
    }


    @Override
    public void enqueueCall(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCall(networkDelegate);

    }

    @Override
    public void enqueueCallSpecificCategory(NetworkDelegate networkDelegate, String str) {
        remoteSource.enqueueCallSpecificCategory(networkDelegate,str);

    }

    @Override

    public void enqueueCallSpecificIngredient(NetworkDelegate networkDelegate, String str) {
        remoteSource.enqueueCallSpecificIngredient(networkDelegate, str);

    }

    public void enqueueCallMeal(NetworkDelegate networkDelegate, String mealName) {
        remoteSource.enqueueCallMeal(networkDelegate,mealName);

    }

    @Override
    public void enqueueCallSpecificCuisine(NetworkDelegate networkDelegate, String cuisineName) {
remoteSource.enqueueCallSpecificCuisine(networkDelegate,cuisineName);
    }

    @Override
    public LiveData<List<MealsDetails>> getAllStoredMeals() {
        return localSource.getAllStoredMeals();
    }

    @Override
    public void addToFavorites(MealsDetails mealsDetails) {
        localSource.addToFavorites(mealsDetails);

    }

    @Override
    public void deleteMealFromFavorites(MealsDetails mealsDetails) {
localSource.deleteMealFromFavorites(mealsDetails);
    }



}
