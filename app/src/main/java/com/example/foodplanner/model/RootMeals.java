package com.example.foodplanner.model;

import java.util.ArrayList;
import java.util.List;

public class RootMeals {
    private List<MealsDetails> mealsDetails= new ArrayList<>();

    public List<MealsDetails> getAllMeals() {
        return mealsDetails;
    }
}
