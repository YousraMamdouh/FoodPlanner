package com.example.foodplanner.searchSpecificCategory.presenter;

import com.example.foodplanner.network.model.MealsDetails;

public interface SpecificCategoryPresenterInterface {

    void getMeals();
    void addToFavorites(MealsDetails mealsDetails);
}
