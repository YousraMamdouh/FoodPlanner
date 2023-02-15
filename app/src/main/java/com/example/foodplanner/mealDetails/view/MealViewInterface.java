package com.example.foodplanner.mealDetails.view;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

public interface MealViewInterface {
    void showMeal(List<MealsDetails> mealsDetails);
    void addMealToFavorites(MealsDetails mealsDetails);
}
