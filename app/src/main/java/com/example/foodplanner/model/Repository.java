package com.example.foodplanner.model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplanner.dataBase.LocalSource;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.network.RemoteSource;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public class Repository implements RepositoryInterface{

    private Context context;
    private RemoteSource remoteSource;
    private LocalSource localSource;
    private static Repository repository=null;
    private boolean isInMyFavorites=false;


    private Repository(RemoteSource remoteSource,LocalSource localSource , Context context)
    {
        this.context=context;
        this.remoteSource = remoteSource;
        this.localSource=localSource;
      //  this.localSource=localSource;

    }

    public static Repository getInstance(RemoteSource remoteSource, LocalSource localSource,Context context){

        if(repository==null)
        {
            repository=new Repository(remoteSource, localSource, context);
        }
        return repository;
    }


    @Override
    public void enqueueCall(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCall(networkDelegate);

    }

    @Override
    public void enqueueCallSpecificCategory(NetworkDelegate networkDelegate, String str) {
        remoteSource.enqueueCallSpecificCategory(networkDelegate,str);

    }

    @Override

    public void enqueueCallSpecificIngredient(NetworkDelegate networkDelegate, String str) {
        remoteSource.enqueueCallSpecificIngredient(networkDelegate, str);

    }

    public void enqueueCallMeal(NetworkDelegate networkDelegate, String mealName) {
        remoteSource.enqueueCallMeal(networkDelegate,mealName);

    }

    @Override
    public void enqueueCallSpecificCuisine(NetworkDelegate networkDelegate, String cuisineName) {
        remoteSource.enqueueCallSpecificCuisine(networkDelegate,cuisineName);
    }

    @Override
    public void enqueueCallDailyInspiration(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCallDailyInspiration(networkDelegate);
    }

    @Override
    public Observable<List<MealsDetails>> getAllStoredMeals() {
        return localSource.getAllStoredMeals();
    }


    @Override
    public void addToFavorites(MealsDetails mealsDetails) {
        localSource.addToFavorites(mealsDetails);

    }

    @Override
    public void deleteMealFromFavorites(MealsDetails mealsDetails) {
localSource.deleteMealFromFavorites(mealsDetails);
    }

    @Override
    public void backupUserData() {
        MealsDetails meal = null;
        String mealId=meal.getIdMeal();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null)
        {
            Toast.makeText(context, "You must login first", Toast.LENGTH_SHORT).show();
        }
else {
    long timestamp=System.currentTimeMillis();
            HashMap<String,Object> hashMap =new HashMap<>();
            hashMap.put("mealID",""mealId);
            hashMap.put("timestamp",""+timestamp);

            //Save to database
            DatabaseReference db= FirebaseDatabase.getInstance().getReference("users");

db.child(firebaseAuth.getUid()).child("favorites").child(mealId)
        .setValue(hashMap)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "backup succeeded ", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "backup failed,please try again later", Toast.LENGTH_SHORT).show();
            }
        });
        }


    }

    @Override
    public void checkIsFavorite() {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null)
        {
            Toast.makeText(context, "You must login first", Toast.LENGTH_SHORT).show();
        }
        else {

            DatabaseReference reference=FirebaseDatabase.getInstance().getReference("users");
            reference.child(firebaseAuth.getUid()).child("favorites").child(mealID)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                isInMyFavorites=snapshot.exists();
                                if (isInMyFavorites)//true if exist, false if not exist
                                {
                                    Toast.makeText(context, "already exists", Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    Toast.makeText(context, "backup succeeded", Toast.LENGTH_SHORT).show();
                                }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }

    }


}
