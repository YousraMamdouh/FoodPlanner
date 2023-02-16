package com.example.foodplanner.searchByCategory.presenter;

import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.searchByCategory.View.CategoriesViewInterface;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.List;

public class CategoriesPresenter implements CategoriesPresenterInterface, NetworkDelegate {

    private CategoriesViewInterface viewInterface;
    private RepositoryInterface repo;

    public CategoriesPresenter(CategoriesViewInterface viewInterface, RepositoryInterface repo) {
        this.viewInterface=viewInterface;
        this.repo = repo;
    }


    @Override
    public void onSuccessAllMeals(List<MealsDetails> mealsDetails) {}

    @Override
    public void onFailureAllMeals(String errorMsg) {}

    @Override
    public void onSuccessAllCategories(List<Categories> categoryItems) {
       viewInterface.showCategories(categoryItems);
        System.out.println("Data retrieved successfully");
    }

    @Override
    public void onFailureAllCategories(String errorMsg) {
        System.out.println("Failed to get categories ");

    }

    @Override
    public void onSuccessAllCountries(List<Countries> countries) {

    }

    @Override
    public void onFailureAllCountries(String errorMsg) {

    }

    @Override
    public void onSuccessAllIngredients(List<Ingredients> ingredients) {

    }

    @Override
    public void onFailureAllIngredients(String errorMsg) {

    }

    @Override
    public void onSuccessSpecificCategory(List<MealsDetails> mealsDetails) {

    }

    @Override
    public void onFailureSpecificCategory(String errorMsg) {

    }

    @Override

    public void onSuccessSpecificIngredient(List<MealsDetails> mealsDetails) {
    }

    @Override
    public void onSuccessMeal(List<MealsDetails> mealsDetails) {

    }


    @Override
    public void onFailureSpecificIngredient(String errorMsg) {

    }
    @Override
    public void onFailureMeal(String errorMsg) {


    }

    @Override
    public void onSuccessSpecificCuisine(List<MealsDetails> mealsDetails) {

    }

    @Override
    public void onFailureSpecificCuisine(String errorMsg) {

    }


    @Override
    public void getCategories() {
        repo.enqueueCall(this);
    }
}
