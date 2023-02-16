package com.example.foodplanner.searchByIngredient.presenter;

import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByIngredient.View.IngredientsViewInterface;
import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.List;

public class IngredientsPresenter implements IngredientsPresenterInterface, NetworkDelegate {

    private IngredientsViewInterface viewInterface;
    private RepositoryInterface repo;

    public IngredientsPresenter(IngredientsViewInterface viewInterface, RepositoryInterface repo) {
        this.viewInterface=viewInterface;
        this.repo = repo;
    }


    @Override
    public void onSuccessAllMeals(List<MealsDetails> mealsDetails) {}

    @Override
    public void onFailureAllMeals(String errorMsg) {}

    @Override
    public void onSuccessAllCategories(List<Categories> categories) {}


    @Override
    public void onFailureAllCategories(String errorMsg) {}

    @Override
    public void onSuccessAllCountries(List<Countries> countries) {}

    @Override
    public void onFailureAllCountries(String errorMsg) {}

    @Override
    public void onSuccessAllIngredients(List<Ingredients> ingredients) {
        viewInterface.showIngredients(ingredients);
        System.out.println("Ingredients retrieved successfully");

    }

    @Override
    public void onFailureAllIngredients(String errorMsg) {
        System.out.println("Failed to get Ingredients");
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
    public void onFailureSpecificIngredient(String errorMsg) {

    }


    @Override
    public void getIngredients() {
        repo.enqueueCall(this);

    }
}
