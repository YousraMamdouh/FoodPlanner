package com.example.foodplanner.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.MealsDetails;

import java.util.List;

@Dao
public interface MealDAO {

    @Query("SELECT * FROM MealDetails")
    LiveData<List<MealsDetails>> getAllMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE) //law el element mawgood abl keda may7tohosh fl database

    void insertMeal(MealsDetails mealsDetails);
    @Delete
  void  deleteMeal(MealsDetails mealsDetails);
}
