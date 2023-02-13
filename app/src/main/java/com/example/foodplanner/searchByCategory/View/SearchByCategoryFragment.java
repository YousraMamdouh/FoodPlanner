package com.example.foodplanner.searchByCategory.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.dataBase.ConcreteLocalSource;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.API_Client;
import com.example.foodplanner.searchByCategory.model.Categories;
import com.example.foodplanner.searchByCategory.presenter.CategoriesPresenter;
import com.example.foodplanner.searchByCategory.presenter.CategoriesPresenterInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchByCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchByCategoryFragment extends Fragment implements CategoriesViewInterface {


    RecyclerView categoryRecyclerView;
    CategoryAdapter categoryAdapter;
    LinearLayoutManager layoutManager;
    CategoriesPresenterInterface categoriesPresenterInterface;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchByCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchByCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchByCategoryFragment newInstance(String param1, String param2) {
        SearchByCategoryFragment fragment = new SearchByCategoryFragment();
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
        View view=inflater.inflate(R.layout.fragment_search_by_category, container, false);
        categoryRecyclerView=view.findViewById(R.id.categoryRecyclerView);


        layoutManager=new LinearLayoutManager(getActivity());
      //  layoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL);

        categoryAdapter= new CategoryAdapter(getActivity(),new ArrayList<>());
        categoriesPresenterInterface= new CategoriesPresenter(this,Repository.getInstance(API_Client.getInstance(), ConcreteLocalSource.getInstance(getActivity()),getActivity()));
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoriesPresenterInterface.getCategories();



        return view;
    }

    @Override
    public void showCategories(List<Categories> categoryItems) {
categoryAdapter.setCategoryItemsList(categoryItems);
categoryAdapter.notifyDataSetChanged();
    }
}