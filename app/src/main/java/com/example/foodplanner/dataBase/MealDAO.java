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

    @Query("SELECT * FROM MealDetails where day like:day" )
    Observable<List<MealsDetails>> getAllMeals(String day);
    @Insert(onConflict = OnConflictStrategy.IGNORE) //law el element mawgood abl keda may7tohosh fl database

    Completable insertMeal(MealsDetails mealsDetails);

    @Delete
  void  deleteMeal(MealsDetails mealsDetails);

    @Query("SELECT * FROM MealDetails where day = :day")
    Observable<List<MealsDetails>> getMealsByDay(String day);

    @Query("SELECT * FROM MealDetails where day !='0'")

    Observable<List<MealsDetails>> getPlannedMeals();


}
