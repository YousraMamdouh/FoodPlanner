package com.example.foodplanner.dataBase;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.model.MealsDetails;

import java.util.List;

public class ConcreteLocalSource implements LocalSource{
    private MealDAO mealDAO;
    private LiveData<List<MealsDetails>> storedMeals;
    private static ConcreteLocalSource localSource=null;
    private ConcreteLocalSource(Context context){

        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO= db.productsDAO();
        storedMeals=mealDAO.getAllMeals();
    }

    public static ConcreteLocalSource getInstance(Context context) {

        if(localSource==null)
        {
            localSource=new ConcreteLocalSource(context);
        }
        return localSource;
    }

    @Override
    public LiveData<List<MealsDetails>> getAllStoredMeals() {
        return storedMeals;
    }

    @Override
    public void addToFavorites(MealsDetails mealsDetails) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertMeal(mealsDetails);
            }
        }).start();



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
}
