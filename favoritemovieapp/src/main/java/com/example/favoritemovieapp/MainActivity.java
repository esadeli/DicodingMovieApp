package com.example.favoritemovieapp;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.favoritemovieapp.adapter.MovieCursorAdapter;
import com.example.favoritemovieapp.data.MovieHelper;

import static com.example.favoritemovieapp.data.DatabaseContract.MovieColumns.CONTENT_URI;

public class MainActivity extends AppCompatActivity {

    private MovieCursorAdapter adapter;
    private MovieHelper movieHelper;
    private Cursor movieCursorList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieCursorAdapter(this);
        adapter.setMovieCursorList(movieCursorList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recView = findViewById(R.id.movie_list_rv);
        recView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recView.getContext(),layoutManager.getOrientation());

        recView.addItemDecoration(dividerItemDecoration);
        recView.setHasFixedSize(true);

        //open database
        movieHelper = new MovieHelper(this);
        movieHelper.open();

        recView.setAdapter(adapter);

        new LoadMovieAsync().execute();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(movieHelper!=null){
            movieHelper.close();
        }
    }

    private class LoadMovieAsync extends AsyncTask<Void,Void,Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return MainActivity.this.getContentResolver().
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
