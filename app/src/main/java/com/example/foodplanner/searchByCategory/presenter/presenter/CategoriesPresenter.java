package com.example.foodplanner.searchByCategory.presenter.presenter;

import com.example.foodplanner.searchByCategory.model.CategoryItems;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.searchByCategory.View.CategoriesViewInterface;

import java.util.List;

public class CategoriesPresenter implements CategoriesPresenterInterface, NetworkDelegate {

    private CategoriesViewInterface viewInterface;
    private RepositoryInterface repo;

    public CategoriesPresenter(CategoriesViewInterface viewInterface, RepositoryInterface repo) {
        this.viewInterface=viewInterface;
        this.repo = repo;
    }



    @Override
    public void onSuccessResult(List<CategoryItems> categoryItems) {
       viewInterface.showCategories(categoryItems);
        System.out.println("Data retrieved successfully");
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
