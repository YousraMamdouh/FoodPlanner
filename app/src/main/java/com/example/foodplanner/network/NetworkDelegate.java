package com.example.foodplanner.network;

import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.searchByCountry.model.Countries;
import com.example.foodplanner.searchByIngredient.model.Ingredients;

import java.util.List;

public interface NetworkDelegate {

    //Meals
    void onSuccessAllMeals(List<MealsDetails> mealsDetails);
    void onFailureAllMeals(String errorMsg);

    //Categories
    void onSuccessAllCategories(List<Categories> categories);
    void onFailureAllCategories(String errorMsg);
    //Countries
    void onSuccessAllCountries(List<Countries> countries);
    void onFailureAllCountries(String errorMsg);

    //Ingredients

    void onSuccessAllIngredients(List<Ingredients> ingredients);
    void onFailureAllIngredients(String errorMsg);
// on daily inspiration
    void onSuccessDailyInspiration(List<MealsDetails> meal);
    void onFailureDailyInspiration(String errorMsg);
//Specific Category
void onSuccessSpecificCategory(List<MealsDetails> mealsDetails);
    void onFailureSpecificCategory(String errorMsg);

    //Specific Ingredients
    void onSuccessSpecificIngredient(List<MealsDetails> mealsDetails);
    void onFailureSpecificIngredient(String errorMsg);


    //Meal
    void onSuccessMeal(List<MealsDetails> mealsDetails);
    void onFailureMeal(String errorMsg);

    //Specific Cuisine
    void onSuccessSpecificCuisine(List<MealsDetails> mealsDetails);
    void onFailureSpecificCuisine(String errorMsg);


}
