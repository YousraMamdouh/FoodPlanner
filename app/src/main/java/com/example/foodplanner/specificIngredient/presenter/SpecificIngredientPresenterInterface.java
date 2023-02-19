package com.example.foodplanner.specificIngredient.presenter;

import com.example.foodplanner.model.MealsDetails;

public interface SpecificIngredientPresenterInterface {

    void getMeals();
    void addToFavorites(MealsDetails mealsDetails);
    void addToCalender(MealsDetails mealsDetails,String day);
}
