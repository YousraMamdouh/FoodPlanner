package com.example.foodplanner.search.presentor;

import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.search.View.AllMealsViewInterface;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.List;

public class AllMealsPresenter implements AllMealsPresenterInterface, NetworkDelegate {

    private AllMealsViewInterface viewInterface;
    private RepositoryInterface repo;

    public AllMealsPresenter(AllMealsViewInterface viewInterface, RepositoryInterface repo) {
        this.viewInterface=viewInterface;
        this.repo = repo;
    }



    @Override
    public void onSuccessAllMeals(List<MealsDetails> mealsDetails) {
        viewInterface.showMeals(mealsDetails);

    }

    @Override
    public void onFailureAllMeals(String errorMsg) {
        System.out.println("Failed to get Meals ");
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
    public void onSuccessDailyInspiration(MealsDetails meal) {

    }

    @Override
    public void onFailureDailyInspiration(String errorMsg) {

    }

    @Override
    public void getMeals() {
        repo.enqueueCall(this);

    }
}
