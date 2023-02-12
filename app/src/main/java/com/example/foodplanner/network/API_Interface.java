package com.example.foodplanner.network;


import com.example.foodplanner.model.RootCategories;
import com.example.foodplanner.model.RootMeals;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface API_Interface {
@GET("random.php")
Observable<RootMeals> getRandomMeal();

@GET("categories.php")
Observable<RootCategories> getAllMealsCategories();

}
