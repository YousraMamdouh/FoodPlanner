package com.example.foodplanner.dataBase;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

import io.reactivex.Observable;

public interface LocalSource {
  //  void enqueueCall(ConnectivityManager.NetworkCallback networkCallback);

   Observable<List<MealsDetails>> getAllStoredMeals();

    void addToFavorites(MealsDetails mealsDetails);

    void deleteMealFromFavorites(MealsDetails mealsDetails);
    void backupUserData();
}
