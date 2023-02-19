package com.example.foodplanner.model;

import java.util.ArrayList;
import java.util.List;

public class RootMeals {
    public void setMeals(List<MealsDetails> meals) {
        this.meals = meals;
    }

    private List<MealsDetails> meals= new ArrayList<>();

    public List<MealsDetails> getAllMeals() {
        return meals;
    }
}
