package com.example.foodplanner.weekPlan.presenter;

import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByIngredient.model.Ingredients;
import com.example.foodplanner.weekPlan.view.WeekPlanViewInterface;

import java.util.List;

import io.reactivex.Observable;

public class WeekPlanPresenter implements NetworkDelegate,WeekPlanPresenterInterface {

    private static final String TAG = "PlanPresenter";

    private WeekPlanViewInterface weekPlanViewInterface;
    private RepositoryInterface repo;

    public  WeekPlanPresenter(WeekPlanViewInterface weekPlanViewInterface, RepositoryInterface repo){
    this.weekPlanViewInterface= weekPlanViewInterface;
    this.repo = repo;
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
    public void onFailureSpecificIngredient(String errorMsg) {

    }

    @Override
    public void onSuccessMeal(List<MealsDetails> mealsDetails) {

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

//    @Override
//    public Observable<List<MealsDetails>> getMyPlannedMeals(String day) {
//        return repo.getStoredMealsByDay(day);
//    }

    @Override
    public Observable<List<MealsDetails>> getMyPlannedMeals(String day) {
        return repo.getStoredMealsByDay(day);
    }

    @Override
    public void removeMealFromPlannedMeal(MealsDetails mealsDetails) {
        repo.deleteMealFromPlan(mealsDetails);
    }
}
