package com.example.foodplanner.network;

public interface RemoteSource {
    void enqueueCall(NetworkDelegate networkDelegate);
    void enqueueCallSpecificCategory(NetworkDelegate networkDelegate,String str);
    void enqueueCallSpecificIngredient(NetworkDelegate networkDelegate,String str);


 //   void getAllMovies(NetworkDelegate networkDelegate);
}
