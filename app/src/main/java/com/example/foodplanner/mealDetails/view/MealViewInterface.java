package com.example.foodplanner.mealDetails.view;

import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.List;

public interface MealViewInterface {
    void showIngredients(List<Ingredients> ingredients);
void showMealDetails(MealsDetails mealsDetails);
    void addMealToFavorites(MealsDetails mealsDetails);
}
