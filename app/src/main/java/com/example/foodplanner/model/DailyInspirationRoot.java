package com.example.foodplanner.model;

import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class DailyInspirationRoot {
    private MealsDetails meal=new MealsDetails();
    public MealsDetails getMeal() {
        return meal;
    }
}