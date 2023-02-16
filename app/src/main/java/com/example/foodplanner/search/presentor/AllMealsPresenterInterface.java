package com.example.foodplanner.search.presentor;

import com.example.foodplanner.network.model.MealsDetails;

public interface AllMealsPresenterInterface {
    void getMeals();
     void addToFavorites(MealsDetails mealsDetails);
}
