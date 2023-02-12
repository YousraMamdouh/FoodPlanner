package com.example.foodplanner.searchByCategory.presenter;

import com.example.foodplanner.searchByCategory.model.CategoryItems;

import java.util.List;

public interface NetworkDelegateForAllCategories {

    //All category API
     void onSuccessAllCategories(List<CategoryItems> categoryItems);
    void onFailureAllCategories(String errorMsg);

    //All meals API

//    void onSuccessAllMeals(List<MealsDetails> mealsDetails);
//    void onFailureAllMeals(String errorMsg);
}
