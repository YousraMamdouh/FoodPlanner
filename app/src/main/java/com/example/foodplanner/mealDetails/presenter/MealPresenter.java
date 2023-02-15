package com.example.foodplanner.mealDetails.presenter;

import com.example.foodplanner.mealDetails.view.MealViewInterface;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.List;

public class MealPresenter implements MealPresenterInterface, NetworkDelegate {

    private MealViewInterface viewInterface;
    private RepositoryInterface repo;
    private String mealName;
 private MealsDetails mealObject;


    public MealPresenter(MealViewInterface viewInterface, RepositoryInterface repo, String mealName) {
        this.viewInterface = viewInterface;
        this.repo = repo;
        this.mealName=mealName;
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
      mealObject=mealsDetails.get(0);
        System.out.println("kolo tamam ");
        viewInterface.showMealDetails(mealObject);
//       mealObject=mealsDetails.get(0);
//
//
//        System.out.println("tamam "+mealObject.getStrMeal());
    //    System.out.println("ana esmy:"+ mealObject.getStrArea()+"W sorty "+mealObject.getStrMealThumb());
    }

    @Override
    public void onFailureMeal(String errorMsg) {

    }

    @Override
    public void getMeal() {
        System.out.println("bnady 3la el meaaaal");
        repo.enqueueCallMeal(this,mealName);

    }

    @Override
    public void addToFavorites(MealsDetails mealsDetails) {
        repo.addToFavorites(mealsDetails);
    }
}
