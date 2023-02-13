package com.example.foodplanner.favorite.presenter;


import com.example.foodplanner.favorite.view.FavoritesViewInterface;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.RepositoryInterface;

public class FavoritesPresenter implements FavoritePresenterInterface{

    FavoritesViewInterface favoritesViewInterface;
    RepositoryInterface repo;

    public FavoritesPresenter(FavoritesViewInterface favoritesViewInterface, RepositoryInterface repo) {
        this.favoritesViewInterface = favoritesViewInterface;
        this.repo=repo;
    }

    @Override
    public void getMyFavorites() {

        favoritesViewInterface.showMeals(repo.getAllStoredMeals());
    }

    @Override
    public void removeMealFromFavorite(MealsDetails mealsDetails) {
        repo.deleteMealFromFavorites(mealsDetails);
    }


}
