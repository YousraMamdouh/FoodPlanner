package com.example.foodplanner.searchByCategory.View.presenter;

import com.example.foodplanner.model.CategoryItems;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.searchByCategory.View.CategoriesViewInterface;

import java.util.List;

public class CategoriesPresenter implements CategoriesPresenterInterface, NetworkDelegate {

    private CategoriesViewInterface categoriesPresenterInterface;
    private RepositoryInterface repo;

    public CategoriesPresenter(CategoriesViewInterface categoriesViewInterface, RepositoryInterface repo) {
        this.categoriesPresenterInterface = categoriesPresenterInterface;
        this.repo = repo;
    }



    @Override
    public void onSuccessResult(List<CategoryItems> categoryItems) {
       categoriesPresenterInterface.showCategories(categoryItems);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        System.out.println("Failed to get data ");

    }


    @Override
    public void getCategories() {
        repo.enqueueCall(this);
    }
}
