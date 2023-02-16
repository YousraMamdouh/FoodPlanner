package com.example.foodplanner.favorite.presenter;


import com.example.foodplanner.model.MealsDetails;

public interface FavoritePresenterInterface {
    void getMyFavorites();
    void removeMealFromFavorite(MealsDetails mealsDetails);
}
