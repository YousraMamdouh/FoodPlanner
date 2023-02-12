package com.example.foodplanner.model;

import com.example.foodplanner.search.presentor.RemoteSourceForAllMeals;
import com.example.foodplanner.searchByCategory.presenter.RemoteSourceForAllCategories;

public interface RepositoryInterface extends RemoteSourceForAllCategories, RemoteSourceForAllMeals {

}
