package com.example.esadeli.dicodingmovieapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.example.esadeli.dicodingmovieapp.adapter.MovieDataAdapter;
import com.example.esadeli.dicodingmovieapp.model.MovieData;
import com.example.esadeli.dicodingmovieapp.utility.fetchDataUtils;
import com.example.esadeli.dicodingmovieapp.internetlink.UrlLink;


/**
 * A simple {@link Fragment} subclass.
 */
//public class SearchFragment extends Fragment implements View.OnClickListener{
public class SearchFragment extends Fragment{

    private ArrayList<MovieData> movieDataList = new ArrayList<>();
    private MovieDataAdapter adapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search,container,false);

        adapter = new MovieDataAdapter(getContext(),movieDataList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recView = view.findViewById(R.id.movie_list_rv2);
        recView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration=
                new DividerItemDecoration(recView.getContext(),layoutManager.getOrientation());

        recView.addItemDecoration(dividerItemDecoration);
        recView.setHasFixedSize(true);

        recView.setAdapter(adapter);


        //Set SearchView
        android.support.v7.widget.SearchView searchView = view.findViewById(R.id.search);
        searchView.setQueryHint(getResources().getString(R.string.search_text));
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieDataList.clear();

                fetchDataUtils.fetchData(getContext(), UrlLink.formUrlSearch(query),movieDataList,adapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }
}
