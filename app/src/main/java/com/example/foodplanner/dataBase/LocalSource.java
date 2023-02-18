package com.example.foodplanner.dataBase;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

import io.reactivex.Observable;

public interface LocalSource {
  //  void enqueueCall(ConnectivityManager.NetworkCallback networkCallback);

   Observable<List<MealsDetails>> getAllStoredMeals();

    void addToFavorites(MealsDetails mealsDetails);

    void deleteMealFromFavorites(MealsDetails mealsDetails);

    void addToFavorites(MealsDetails mealsDetails, String day);

    Observable<List<MealsDetails>> getStoredMealsByDay(String day);

    Observable<List<MealsDetails>> getPlannedMeals();
    void deleteMealFromPlan(MealsDetails mealsDetails);
}
