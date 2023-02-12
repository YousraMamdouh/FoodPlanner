package com.example.foodplanner.searchByIngredient.model;

import java.util.ArrayList;
import java.util.List;

public class RootIngredients {
    private List<Ingredients> meals=new ArrayList<>();
    public List<Ingredients> getIngredients() {
        return meals;
    }
}
