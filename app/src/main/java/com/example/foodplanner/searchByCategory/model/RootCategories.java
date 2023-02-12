package com.example.foodplanner.searchByCategory.model;

import java.util.ArrayList;
import java.util.List;

public class RootCategories {
    private List<CategoryItems> categories=new ArrayList<>();
    public List<CategoryItems> getCategoryItems() {
        return categories;
    }
}
