package com.example.foodplanner.spcificIngredient.view;

import com.example.foodplanner.network.model.MealsDetails;

import java.util.List;

public interface SpecificIngredientViewInterface {

    void showMeals(List<MealsDetails> mealsDetails);
    void addMealToFavorites(MealsDetails mealsDetails);
}
