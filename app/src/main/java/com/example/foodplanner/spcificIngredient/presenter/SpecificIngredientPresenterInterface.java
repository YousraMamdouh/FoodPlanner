package com.example.foodplanner.spcificIngredient.presenter;

import com.example.foodplanner.network.model.MealsDetails;

public interface SpecificIngredientPresenterInterface {

    void getMeals();
    void addToFavorites(MealsDetails mealsDetails);
}
