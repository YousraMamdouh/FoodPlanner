package com.example.foodplanner.searchByCountry.presenter;

import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.searchByCountry.View.CountriesViewInterface;
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.List;

public class CountriesPresenter implements CountriesPresenterInterface, NetworkDelegate {

    private CountriesViewInterface viewInterface;
    private RepositoryInterface repo;

    public CountriesPresenter(CountriesViewInterface viewInterface, RepositoryInterface repo) {
        this.viewInterface=viewInterface;
        this.repo = repo;
    }


    @Override
    public void onSuccessAllMeals(List<MealsDetails> mealsDetails) {}

    @Override
    public void onFailureAllMeals(String errorMsg) {}

    @Override
    public void onSuccessAllCategories(List<Categories> categories) {

    }


    @Override
    public void onFailureAllCategories(String errorMsg) {
        System.out.println("Failed to get categories ");

    }

    @Override
    public void onSuccessAllCountries(List<Countries> countries) {
        viewInterface.showCountries(countries);
        System.out.println("Countries retrieved successfully");
    }

    @Override
    public void onFailureAllCountries(String errorMsg) {
        System.out.println("Can't retrieve countries ");

    }

    @Override
    public void onSuccessAllIngredients(List<Ingredients> ingredients) {

    }

    @Override
    public void onFailureAllIngredients(String errorMsg) {

    }

    @Override
    public void onSuccessDailyInspiration(List<MealsDetails> meal) {

    }

    @Override
    public void onFailureDailyInspiration(String errorMsg) {

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
    public void getCountries() {
repo.enqueueCall(this);
    }
}
