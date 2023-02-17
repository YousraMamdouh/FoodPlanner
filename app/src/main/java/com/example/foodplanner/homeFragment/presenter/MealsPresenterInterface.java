package com.example.foodplanner.homeFragment.presenter;

import com.example.foodplanner.model.MealsDetails;

public interface MealsPresenterInterface {
    void getMeals();
    void getDailyInspiration();
    void addToFavorites(MealsDetails mealsDetails);
}
