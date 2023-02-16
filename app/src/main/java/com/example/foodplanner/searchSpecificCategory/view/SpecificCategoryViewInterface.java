package com.example.foodplanner.searchSpecificCategory.view;

import com.example.foodplanner.network.model.MealsDetails;

import java.util.List;

public interface SpecificCategoryViewInterface {

    void showMeals(List<MealsDetails> mealsDetails);
   void addMealToFavorites(MealsDetails mealsDetails);
}
