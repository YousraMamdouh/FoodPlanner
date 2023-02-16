package com.example.foodplanner.specificIngredient.view;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

public interface SpecificIngredientViewInterface {

    void showMeals(List<MealsDetails> mealsDetails);
    void addMealToFavorites(MealsDetails mealsDetails);
}
