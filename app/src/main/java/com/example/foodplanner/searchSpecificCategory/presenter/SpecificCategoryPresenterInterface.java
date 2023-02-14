package com.example.foodplanner.searchSpecificCategory.presenter;

import com.example.foodplanner.model.MealsDetails;

public interface SpecificCategoryPresenterInterface {
    void getMeals();
    void addToFavorites(MealsDetails mealsDetails);
}
