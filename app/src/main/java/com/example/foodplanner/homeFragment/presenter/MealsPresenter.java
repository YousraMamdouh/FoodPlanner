package com.example.foodplanner.homeFragment.presenter;

import com.example.foodplanner.homeFragment.View.HomeMealsViewInterface;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.RepositoryInterface;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.search.View.AllMealsViewInterface;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.List;
import java.util.Random;

public class MealsPresenter implements MealsPresenterInterface, NetworkDelegate {

    private HomeMealsViewInterface viewInterface;
    private RepositoryInterface repo;

    public MealsPresenter(HomeMealsViewInterface viewInterface, RepositoryInterface repo) {
        this.viewInterface=viewInterface;
        this.repo = repo;
    }

    @Override
    public void getMeals() {
        repo.enqueueCall(this);
        System.out.println("enqueue for inspiration");
    }

    @Override
    public void getDailyInspiration() {
        repo.enqueueCall(this);
    }

    @Override
    public void onSuccessAllMeals(List<MealsDetails> mealsDetails) {
        viewInterface.showMeals(mealsDetails);
        System.out.println("presenter meals");
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
        System.out.println("presenter inspiration");

//     int randomNo=getRandomNumber(meal);
//        viewInterface.showDailyInspiration(meal.get(meal.indexOf(randomNo)));

    }



    @Override
    public void onFailureDailyInspiration(String errorMsg) {

    }

//    public int getRandomNumber(List<MealsDetails> meal){
//
//        Random random = new Random();
//        int randomNumber = random.nextInt(0 - meal.size() );
//        return  randomNumber;
//    }

}
