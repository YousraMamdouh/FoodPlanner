package com.example.foodplanner.network;

public interface RemoteSource {
    void enqueueCall(NetworkDelegate networkDelegate);
    void enqueueCallSpecificCategory(NetworkDelegate networkDelegate,String categoryName);
    void enqueueCallMeal(NetworkDelegate networkDelegate,String mealName);



 //   void getAllMovies(NetworkDelegate networkDelegate);
}
