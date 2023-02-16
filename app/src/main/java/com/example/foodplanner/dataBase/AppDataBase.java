package com.example.foodplanner.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.MealsDetails;


@Database(entities = {MealsDetails.class},version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance=null;
    public abstract MealDAO productsDAO();
    public static synchronized AppDataBase getInstance(Context context){
        if(instance==null)
        {
           instance= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"Meals_db").build();

        }
        return instance;
    }
}
