package com.example.foodplanner.network.model;

import java.util.ArrayList;
import java.util.List;

public class RootMeals {
    private List<MealsDetails> meals= new ArrayList<>();

    public List<MealsDetails> getAllMeals() {
        return meals;
    }
}
