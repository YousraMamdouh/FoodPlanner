package com.example.foodplanner.dataBase;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ConcreteLocalSource implements LocalSource{
    private MealDAO mealDAO;
    private Observable<List<MealsDetails>> storedMeals, saturdayMeals, sundayMeals, mondayMeals, tuesdayMeals,
    wednesdayMeals, thursdayMeals, fridayMeals, plannedMeals;

    private static ConcreteLocalSource localSource=null;
    private ConcreteLocalSource(Context context){

        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO= db.productsDAO();
        plannedMeals = mealDAO.getPlannedMeals();
        storedMeals=mealDAO.getAllMeals("0");
        saturdayMeals = mealDAO.getMealsByDay("saturday");
        sundayMeals = mealDAO.getMealsByDay("sunday");
        mondayMeals = mealDAO.getMealsByDay("monday");
        tuesdayMeals = mealDAO.getMealsByDay("tuesday");
        thursdayMeals = mealDAO.getMealsByDay("thursday");
        wednesdayMeals = mealDAO.getMealsByDay("wednesday");
        fridayMeals = mealDAO.getMealsByDay("friday");
    }


    public static ConcreteLocalSource getInstance(Context context) {

        if(localSource==null)
        {
            localSource=new ConcreteLocalSource(context);
        }
        return localSource;
    }

    @Override
    public Observable<List<MealsDetails>> getAllStoredMeals() {
        return storedMeals;
    }

    @Override
    public void addToFavorites(MealsDetails mealsDetails) {
        mealDAO.insertMeal(mealsDetails).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mealDAO.insertMeal(mealsDetails);


                    }

                    @Override
                    public void onComplete() {
                        System.out.println("OnComplete meal added to the database");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("An error occurred while adding the meal to database");
                    }
                });

    }

    @Override
    public void deleteMealFromFavorites(MealsDetails mealsDetails) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteMeal(mealsDetails);
            }
        }).start();
    }

    @Override
    public void addToFavorites(MealsDetails mealsDetails, String day) {
        mealsDetails.setDay( day);
        mealDAO.insertMeal(mealsDetails).subscribeOn(Schedulers.io())


                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mealDAO.insertMeal(mealsDetails);
                        System.out.println("day wsl");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("OnComplete meal added to the database");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("An error occurred while adding the meal to database");
                    }
                });


    }

    @Override
    public Observable<List<MealsDetails>> getStoredMealsByDay(String day) {
        switch (day) {
            case "saturday":
                return saturdayMeals;
            case "sunday":
                return sundayMeals;
            case "monday":
                return mondayMeals;
            case "tuesday":
                return tuesdayMeals;
            case "wednesday":
                return wednesdayMeals;
            case "thursday":
                return thursdayMeals;
            case "friday":
                return fridayMeals;
            default:
                return null;

        }
    }

    @Override
    public Observable<List<MealsDetails>> getPlannedMeals() {
        return null;
    }

    @Override
    public void deleteMealFromPlan(MealsDetails mealsDetails) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteMeal(mealsDetails);
            }
        }).start();
    }


    @Override
    public void backupUserData() {

    }


}
