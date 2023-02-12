package com.example.foodplanner.network;

import com.example.foodplanner.searchByCategory.model.CategoryItems;


import java.util.List;

public interface NetworkDelegate {
    public void onSuccessResult(List<CategoryItems> categoryItems);
    public void onFailureResult(String errorMsg);
}
