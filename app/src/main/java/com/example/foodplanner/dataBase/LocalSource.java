package com.example.foodplanner.dataBase;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.model.MealsDetails;

import java.util.List;

public interface LocalSource {
  //  void enqueueCall(ConnectivityManager.NetworkCallback networkCallback);

    LiveData<List<MealsDetails>> getAllStoredMeals();

    void addToFavorites(MealsDetails mealsDetails);

    void deleteMealFromFavorites(MealsDetails mealsDetails);
}
