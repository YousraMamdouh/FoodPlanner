package com.example.foodplanner.network;

public interface RemoteSource {
    void enqueueCall(NetworkDelegate networkDelegate);
    void enqueueCallSpecificCategory(NetworkDelegate networkDelegate,String str);
    void enqueueCallMeal(NetworkDelegate networkDelegate,String mealName);
    void enqueueCallSpecificCuisine(NetworkDelegate networkDelegate,String cuisineName);



 //   void getAllMovies(NetworkDelegate networkDelegate);
}
