package com.example.foodplanner.dataBase;

import android.content.Context;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ConcreteLocalSource implements LocalSource{
    private MealDAO mealDAO;
    private Observable<List<MealsDetails>> storedMeals;



    private static ConcreteLocalSource localSource=null;
    private ConcreteLocalSource(Context context){

        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO= db.productsDAO();
        storedMeals=mealDAO.getAllMeals("0");
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

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mealDAO.insertMeal(mealsDetails);
//            }
//        }).start();



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
    public void backupUserData() {

    }


}
