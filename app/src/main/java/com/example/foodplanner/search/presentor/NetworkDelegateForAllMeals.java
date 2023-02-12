package com.example.foodplanner.search.presentor;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

public interface NetworkDelegateForAllMeals {


    void onSuccessAllMeals(List<MealsDetails> mealsDetails);
    void onFailureAllMeals(String errorMsg);
}
