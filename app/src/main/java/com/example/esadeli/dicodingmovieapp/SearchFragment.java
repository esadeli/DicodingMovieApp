package com.example.esadeli.dicodingmovieapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import com.example.esadeli.dicodingmovieapp.utility.fetchDataUtils;
import com.example.esadeli.dicodingmovieapp.data.urlLink;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener{

    private ArrayList<movieData> movieDataList = new ArrayList<>();
    private movieDataAdapter adapter;

    private EditText searchEditText;
    private String title;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search,container,false);

        //Instantiate Button and EditText object
        Button searchBtn = view.findViewById(R.id.btn_search);
        searchBtn.setOnClickListener(this);

        searchEditText = view.findViewById(R.id.searchEditText);


        adapter = new movieDataAdapter(getContext(),movieDataList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recView = view.findViewById(R.id.movie_list_rv2);
        recView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration=
                new DividerItemDecoration(recView.getContext(),layoutManager.getOrientation());

        recView.addItemDecoration(dividerItemDecoration);
        recView.setHasFixedSize(true);

        recView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_search){

            title = searchEditText.getText().toString().trim();

            movieDataList.clear();
            fetchDataUtils.fetchData(getContext(),urlLink.formUrlSearch(title),movieDataList,adapter);
        }
    }
}
