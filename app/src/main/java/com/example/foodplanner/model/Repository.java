package com.example.foodplanner.model;

import android.content.Context;

import com.example.foodplanner.search.presentor.NetworkDelegateForAllMeals;
import com.example.foodplanner.search.presentor.RemoteSourceForAllMeals;
import com.example.foodplanner.searchByCategory.presenter.NetworkDelegateForAllCategories;
import com.example.foodplanner.searchByCategory.presenter.RemoteSourceForAllCategories;

public class Repository implements RepositoryInterface{

    private Context context;
    private RemoteSourceForAllCategories remoteSourceForAllCategories;
    private RemoteSourceForAllMeals remoteSourceForAllMeals;
    private static Repository repository=null;


    private Repository(RemoteSourceForAllCategories remoteSourceForAllCategories, Context context)
    {
        this.context=context;
        this.remoteSourceForAllCategories = remoteSourceForAllCategories;
      //  this.localSource=localSource;

    }

    public static Repository getInstance(RemoteSourceForAllCategories remoteSourceForAllCategories, Context context){

        if(repository==null)
        {
            repository=new Repository(remoteSourceForAllCategories,context);
        }
        return repository;
    }

    @Override
    public void enqueueCallCategories(NetworkDelegateForAllCategories networkDelegateForAllCategories) {
        remoteSourceForAllCategories.enqueueCallCategories(networkDelegateForAllCategories);
    }

    @Override
    public void enqueueCallMeals(NetworkDelegateForAllMeals networkDelegateForAllMeals) {
        remoteSourceForAllMeals.enqueueCallMeals(networkDelegateForAllMeals);
    }
}
