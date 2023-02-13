package com.example.foodplanner.model;

import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class DailyInspirationRoot {
    private List<MealsDetails>  meals=new ArrayList<>();
    public List<MealsDetails> getMeal() {
        return meals;
    }
}