package com.example.foodplanner.favorite.view;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

import io.reactivex.Observable;

public interface FavoritesViewInterface {
    public void deleteMeal(MealsDetails mealsDetails);
    public void showMeals(Observable<List<MealsDetails>> mealsDetails);

}
