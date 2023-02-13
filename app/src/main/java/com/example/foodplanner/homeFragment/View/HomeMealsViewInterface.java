package com.example.foodplanner.homeFragment.View;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

public interface HomeMealsViewInterface {
    void showMeals(List<MealsDetails> mealsDetails);
     void showDailyInspiration(MealsDetails meal);
}
