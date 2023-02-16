package com.example.foodplanner.favorite.view;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.model.MealsDetails;

import java.util.List;

public interface FavoritesViewInterface {
    public void deleteMeal(MealsDetails mealsDetails);
    public void showMeals(LiveData<List<MealsDetails>> mealsDetails);

}
