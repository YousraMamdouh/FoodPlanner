package com.example.foodplanner.SpecificCuisine.view;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

public interface SpecificCuisineViewInterface {

    void showMeals(List<MealsDetails> mealsDetails);
   void addMealToFavorites(MealsDetails mealsDetails);
}
