package com.example.foodplanner.weekPlan.presenter;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

import io.reactivex.Observable;

public interface WeekPlanPresenterInterface {
    public Observable<List<MealsDetails>>  getMyPlannedMeals(String day);
    void removeMealFromPlannedMeal(MealsDetails mealsDetails);
}
