package com.example.foodplanner.mealDetails.presenter;

import com.example.foodplanner.model.MealsDetails;

public interface MealPresenterInterface {

    void getMeal();
    void addToFavorites(MealsDetails mealsDetails);
}
