package com.example.foodplanner.favorite.view;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

import io.reactivex.Observable;

public interface FavoritesViewInterface {
 void deleteMeal(MealsDetails mealsDetails);
 void showMeals(Observable<List<MealsDetails>> mealsDetails);

}
