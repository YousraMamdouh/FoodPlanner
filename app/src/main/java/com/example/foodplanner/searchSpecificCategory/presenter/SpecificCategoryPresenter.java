package com.example.foodplanner.searchSpecificCategory.presenter;

import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByIngredient.model.Ingredients;
import com.example.foodplanner.searchSpecificCategory.view.SpecificCategoryViewInterface;

import java.util.List;

public class SpecificCategoryPresenter implements SpecificCategoryPresenterInterface, NetworkDelegate {

    private SpecificCategoryViewInterface viewInterface;
    private RepositoryInterface repo;
    private String categoryName;

    public SpecificCategoryPresenter(SpecificCategoryViewInterface viewInterface, RepositoryInterface repo,String categoryName) {
        this.viewInterface=viewInterface;
        this.repo = repo;
        this.categoryName = categoryName;
    }



    @Override
    public void onSuccessAllMeals(List<MealsDetails> mealsDetails) {
    }

    @Override
    public void onFailureAllMeals(String errorMsg) {
    }

    @Override
    public void onSuccessAllCategories(List<Categories> categoryItems) {}

    @Override
    public void onFailureAllCategories(String errorMsg) {}

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
        viewInterface.showMeals(mealsDetails);
        System.out.println("Data retrieved successfully");
    }

    @Override
    public void onFailureSpecificCategory(String errorMsg) {

    }

    @Override

    public void onSuccessSpecificIngredient(List<MealsDetails> mealsDetails) {

    public void onSuccessMeal(List<MealsDetails> mealsDetails) {


    }

    @Override

    public void onFailureSpecificIngredient(String errorMsg) {

    public void onFailureMeal(String errorMsg) {


    }

    @Override
    public void getMeals() {
        repo.enqueueCallSpecificCategory(this, categoryName);


    }

    @Override
    public void addToFavorites(MealsDetails mealsDetails) {
        repo.addToFavorites(mealsDetails);
    }
}
