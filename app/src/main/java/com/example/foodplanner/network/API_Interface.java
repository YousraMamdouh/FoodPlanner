package com.example.foodplanner.network;


import com.example.foodplanner.model.DailyInspirationRoot;
import com.example.foodplanner.model.RootMeals;
import com.example.foodplanner.searchByCategory.model.RootCategories;
import com.example.foodplanner.searchByCountry.model.RootCountries;
import com.example.foodplanner.searchByIngredient.model.RootIngredients;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Interface {


@GET("categories.php")
Observable<RootCategories> getAllMealsCategories();

@GET("search.php?s")
    Observable<RootMeals> getAllMeals();

@GET("list.php?a=list")
    Observable<RootCountries> getAllCountries();
@GET("list.php?i=list")
    Observable<RootIngredients> getAllIngredients();

    @GET("filter.php")
    Observable<RootMeals> getMealsOfSelectedCountry(@Query("a") String selectedArea);

    @GET("filter.php")
    Observable<RootMeals> getMealsOfSelectedCategory(@Query("c") String selectedCategory);

    @GET("filter.php")
    Observable<RootMeals> getMealsOfSelectedIngredient(@Query("i") String selectedIngredient);

    @GET("search.php")
    Observable<RootMeals> getMeal(@Query("s") String mealName);

    @GET("random.php")
    Observable<RootMeals> getRandomMeal( );

}
