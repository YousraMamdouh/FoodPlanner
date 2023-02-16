package com.example.foodplanner.specificIngredient.presenter;

import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByIngredient.model.Ingredients;
import com.example.foodplanner.specificIngredient.view.SpecificIngredientViewInterface;

import java.util.List;

public class SpecificIngredientPresenter implements SpecificIngredientPresenterInterface, NetworkDelegate {

    private SpecificIngredientViewInterface  viewInterface;
    private RepositoryInterface repo;
    private String ingredient;
    public SpecificIngredientPresenter(SpecificIngredientViewInterface viewInterface, RepositoryInterface repo, String ingredient) {
        this.viewInterface=viewInterface;
        this.repo = repo;
        this.ingredient=ingredient;
    }


    @Override
    public void onSuccessAllMeals(List<MealsDetails> mealsDetails) {

    }

    @Override
    public void onFailureAllMeals(String errorMsg) {

    }

    @Override
    public void onSuccessAllCategories(List<Categories> categories) {

    }

    @Override
    public void onFailureAllCategories(String errorMsg) {

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
        viewInterface.showMeals(mealsDetails);
        System.out.println("Data retrieved successfully");
    }

    @Override
    public void onFailureSpecificIngredient(String errorMsg) {

    }

    @Override
    public void getMeals() {
        System.out.println("ingredient");
        repo.enqueueCallSpecificIngredient(this,ingredient);
    }

    @Override
    public void addToFavorites(MealsDetails mealsDetails) {

    }
}
