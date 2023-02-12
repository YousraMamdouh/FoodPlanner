package com.example.foodplanner.model;

import android.content.Context;

import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.network.RemoteSource;

public class Repository implements RepositoryInterface{

    private Context context;
    private RemoteSource remoteSource;
    private static Repository repository=null;


    private Repository(RemoteSource remoteSource,  Context context)
    {
        this.context=context;
        this.remoteSource = remoteSource;
      //  this.localSource=localSource;

    }

    public static Repository getInstance(RemoteSource remoteSource, Context context){

        if(repository==null)
        {
            repository=new Repository(remoteSource,context);
        }
        return repository;
    }


    @Override
    public void enqueueCall(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCall(networkDelegate);
    }
}
