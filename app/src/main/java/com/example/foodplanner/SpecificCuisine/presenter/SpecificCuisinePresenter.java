package com.example.foodplanner.SpecificCuisine.presenter;

import com.example.foodplanner.SpecificCuisine.view.SpecificCuisineViewInterface;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.List;

public class SpecificCuisinePresenter implements SpecificCuisinePresenterInterface, NetworkDelegate {

    private SpecificCuisineViewInterface viewInterface;
    private RepositoryInterface repo;
    private String cuisineName;

    public SpecificCuisinePresenter(SpecificCuisineViewInterface viewInterface, RepositoryInterface repo, String cuisineName) {
        this.viewInterface=viewInterface;
        this.repo = repo;
        this.cuisineName=cuisineName;
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

    }

    @Override
    public void onFailureSpecificCategory(String errorMsg) {

    }

    @Override
    public void onSuccessMeal(List<MealsDetails> mealsDetails) {

    }

    @Override
    public void onFailureMeal(String errorMsg) {

    }

    @Override
    public void onSuccessSpecificCuisine(List<MealsDetails> mealsDetails) {
        viewInterface.showMeals(mealsDetails);
    }

    @Override
    public void onFailureSpecificCuisine(String errorMsg) {

    }

    @Override
    public void getMeals() {
        repo.enqueueCallSpecificCategory(this, cuisineName);


    }

    @Override
    public void addToFavorites(MealsDetails mealsDetails) {
        repo.addToFavorites(mealsDetails);
    }
}
