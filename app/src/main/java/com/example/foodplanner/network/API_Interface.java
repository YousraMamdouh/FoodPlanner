package com.example.foodplanner.network;


import com.example.foodplanner.searchByCategory.model.RootCategories;
import com.example.foodplanner.model.RootMeals;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface API_Interface {


@GET("categories.php")
Observable<RootCategories> getAllMealsCategories();



}
