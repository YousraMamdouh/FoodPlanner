package com.example.foodplanner.network;

import com.example.foodplanner.model.CategoryItems;
import com.example.foodplanner.model.MealsDetails;


import java.util.List;

public interface NetworkDelegate {
    public void onSuccessResult(List<CategoryItems> categoryItems);
    public void onFailureResult(String errorMsg);
}
