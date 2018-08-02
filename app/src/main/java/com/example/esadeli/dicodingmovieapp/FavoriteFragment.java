package com.example.esadeli.dicodingmovieapp;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.esadeli.dicodingmovieapp.adapter.MovieCursorAdapter;
import com.example.esadeli.dicodingmovieapp.data.MovieHelper;

import static com.example.esadeli.dicodingmovieapp.data.DatabaseContract.MovieColumns.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    private MovieCursorAdapter adapter;
    private MovieHelper movieHelper;
    private Cursor movieCursorList;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,container,false);

        adapter = new MovieCursorAdapter(getContext());
        adapter.setMovieCursorList(movieCursorList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recView = view.findViewById(R.id.movie_list_rv);
        recView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recView.getContext(),layoutManager.getOrientation());

        recView.addItemDecoration(dividerItemDecoration);
        recView.setHasFixedSize(true);

        //open database
        movieHelper = new MovieHelper(getContext());
        movieHelper.open();


        recView.setAdapter(adapter);



        new LoadMovieAsync().execute();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(movieHelper!=null){
            movieHelper.close();
        }
    }

    private class LoadMovieAsync extends AsyncTask<Void,Void,Cursor>{

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContext().getContentResolver().
                    query(CONTENT_URI,
                            null,
                            null,
                            null,
                            null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            movieCursorList = cursor;
            adapter.setMovieCursorList(movieCursorList);
            adapter.notifyDataSetChanged();

        }
    }

}
