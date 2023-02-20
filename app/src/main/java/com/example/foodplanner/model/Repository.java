package com.example.foodplanner.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplanner.dataBase.LocalSource;
import com.example.foodplanner.network.NetworkDelegate;
import com.example.foodplanner.network.RemoteSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Repository implements RepositoryInterface {

    private Context context;
    private RemoteSource remoteSource;
    private LocalSource localSource;
    private static Repository repository = null;
    //    private boolean isInMyFavorites=false;
    private static final String Tag = "observable";
    private RootMeals rootMeals;


    private Repository(RemoteSource remoteSource, LocalSource localSource, Context context) {
        this.context = context;
        this.remoteSource = remoteSource;
        this.localSource = localSource;
    }

    public static Repository getInstance(RemoteSource remoteSource, LocalSource localSource, Context context) {

        if (repository == null) {
            repository = new Repository(remoteSource, localSource, context);
        }
        return repository;
    }


    @Override
    public void enqueueCall(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCall(networkDelegate);

    }

    @Override
    public void enqueueCallSpecificCategory(NetworkDelegate networkDelegate, String str) {
        remoteSource.enqueueCallSpecificCategory(networkDelegate, str);

    }

    @Override

    public void enqueueCallSpecificIngredient(NetworkDelegate networkDelegate, String str) {
        remoteSource.enqueueCallSpecificIngredient(networkDelegate, str);

    }

    public void enqueueCallMeal(NetworkDelegate networkDelegate, String mealName) {
        remoteSource.enqueueCallMeal(networkDelegate, mealName);

    }

    @Override
    public void enqueueCallSpecificCuisine(NetworkDelegate networkDelegate, String cuisineName) {
        remoteSource.enqueueCallSpecificCuisine(networkDelegate, cuisineName);
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
        System.out.println("www");

    }

    @Override
    public void deleteMealFromFavorites(MealsDetails mealsDetails) {
        localSource.deleteMealFromFavorites(mealsDetails);
    }

    @Override
    public void addToFavorites(MealsDetails mealsDetails, String day) {
        localSource.addToFavorites(mealsDetails, day);
        System.out.println("bbbbb");
    }

    @Override
    public Observable<List<MealsDetails>> getStoredMealsByDay(String day) {
        return localSource.getStoredMealsByDay(day);
    }

    @Override
    public Observable<List<MealsDetails>> getPlannedMeals() {
        return localSource.getPlannedMeals();
    }

    @Override
    public void deleteMealFromPlan(MealsDetails mealsDetails) {
        localSource.deleteMealFromPlan(mealsDetails);
    }

    @Override
    public void backupUserData() {
        RootMeals rootMeals = new RootMeals();
        getAllStoredMeals().subscribeOn(Schedulers.io()).subscribe(new Observer<List<MealsDetails>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(Tag, "onSubscribe");
            }

            @Override
            public void onNext(List<MealsDetails> mealsDetails) {
                rootMeals.setMeals(mealsDetails);

                checkFirebase(rootMeals);


            }

            @Override
            public void onError(Throwable e) {
                Log.i(Tag, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(Tag, "onComplete");

            }
        });


    }


    public void checkFirebase(RootMeals rootMeals) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(context, "You must login first", Toast.LENGTH_SHORT).show();
        } else {


            //Save to database
            DatabaseReference db = FirebaseDatabase.getInstance().getReference("Registered users");
            db.child(firebaseAuth.getUid()).child("favorites")
                    .setValue(rootMeals)

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
    public void retrieveFavFromFirebase(String userEmail) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Registered Users").child(firebaseAuth.getUid()).child("favorites");
        ref.child(userEmail).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        RootMeals userFav = dataSnapshot.child("favorites").getValue(RootMeals.class);
                        rootMeals = userFav;


                    } else {
                        Toast.makeText(context, "user doesn't exist", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(context, "Failed to read the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


//    @Override
//    public void checkIsFavorite() {
//        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
//
//        if(firebaseAuth.getCurrentUser()==null)
//        {
//            Toast.makeText(context, "You must login first", Toast.LENGTH_SHORT).show();
//        }
//        else {
//
//            DatabaseReference reference=FirebaseDatabase.getInstance().getReference("users");
//            reference.child(firebaseAuth.getUid()).child("favorites").child(mealID)
//                    .addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                                isInMyFavorites=snapshot.exists();
//                                if (isInMyFavorites)//true if exist, false if not exist
//                                {
//                                    Toast.makeText(context, "already exists", Toast.LENGTH_SHORT).show();
//
//                                }
//                                else {
//                                    Toast.makeText(context, "backup succeeded", Toast.LENGTH_SHORT).show();
//                                }
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//        }
//
//    }


}
