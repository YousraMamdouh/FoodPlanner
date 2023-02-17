package com.example.foodplanner.dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface MealDAO {

    @Query("SELECT * FROM MealDetails")
    Observable<List<MealsDetails>> getAllMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE) //law el element mawgood abl keda may7tohosh fl database

    Completable insertMeal(MealsDetails mealsDetails);
    @Delete
  void  deleteMeal(MealsDetails mealsDetails);
}
