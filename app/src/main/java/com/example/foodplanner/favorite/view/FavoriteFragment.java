package com.example.foodplanner.favorite.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.foodplanner.R;
import com.example.foodplanner.dataBase.ConcreteLocalSource;
import com.example.foodplanner.favorite.presenter.FavoritePresenterInterface;
import com.example.foodplanner.favorite.presenter.FavoritesPresenter;
import com.example.foodplanner.model.MealsDetails;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.API_Client;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment implements OnDeleteClickListener ,FavoritesViewInterface{
    RecyclerView favRecyclerView;
    FavoritesAdapter favoritesAdapter;
    StaggeredGridLayoutManager layoutManager;
    FavoritePresenterInterface favoritePresenterInterface;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_favorite, container, false);
        favRecyclerView=view.findViewById(R.id.favRecyclerView);
        favoritePresenterInterface=new FavoritesPresenter(this, Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getActivity()),getActivity()));
        favoritesAdapter=new FavoritesAdapter(getActivity(),new ArrayList<>(),this);
//        layoutManager=new LinearLayoutManager(getActivity());
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        favRecyclerView.setLayoutManager(layoutManager);
        favRecyclerView.setAdapter(favoritesAdapter);
        favoritePresenterInterface.getMyFavorites();
        return view;
    }

    @Override
    public void onClick(MealsDetails mealsDetails) {
        Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
        deleteMeal(mealsDetails);

    }

    @Override
    public void deleteMeal(MealsDetails mealsDetails) {
        favoritePresenterInterface.removeMealFromFavorite(mealsDetails);

    }

    @Override
    public void showMeals(Observable<List<MealsDetails>> mealsDetails) {
        mealsDetails.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<List<MealsDetails>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("OnSubscribe Observer");
                    }

                    @Override
                    public void onNext(List<MealsDetails> mealsDetails) {
                        favoritesAdapter.setList(mealsDetails);
                        favoritesAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("an error occurred while showing meals");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Showing meals completed");
                    }
                });
    }


}