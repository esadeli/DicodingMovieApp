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
public class UpcomingFragment extends Fragment {

    private ArrayList<MovieData> movieDataList = new ArrayList<>();
    private MovieDataAdapter adapter;


    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,container,false);

        adapter = new MovieDataAdapter(getContext(),movieDataList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recView = view.findViewById(R.id.movie_list_rv);
        recView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recView.getContext(),layoutManager.getOrientation());

        recView.addItemDecoration(dividerItemDecoration);
        recView.setHasFixedSize(true);

        recView.setAdapter(adapter);

        movieDataList.clear();

        //fetch data from Internet
        fetchDataUtils.fetchData(getContext(), UrlLink.urlUpcoming,movieDataList,adapter);

        return view;
    }

}
