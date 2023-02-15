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
    private String category;

    public SpecificCategoryPresenter(SpecificCategoryViewInterface viewInterface, RepositoryInterface repo,String category) {
        this.viewInterface=viewInterface;
        this.repo = repo;
        this.category=category;
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
    public void onSuccessMeal(List<MealsDetails> mealsDetails) {

    }

    @Override
    public void onFailureMeal(String errorMsg) {

    }

    @Override
    public void getMeals() {
        System.out.println("bnady 3aleh");
        repo.enqueueCallSpecificCategory(this,category);

    }

    @Override
    public void addToFavorites(MealsDetails mealsDetails) {
        repo.addToFavorites(mealsDetails);
    }
}
