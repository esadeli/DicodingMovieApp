package com.example.esadeli.dicodingmovieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Updating GitHub due to the first push did not involve all files in the DicodingMovieApp folder
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String ADULTPARAM = "false";


    private ArrayList<movieData> movieDataList = new ArrayList<>();

    private movieDataAdapter adapter;
    private EditText search;
    private String searchByTitle; //Dinosaur used for testing


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchBtn = (Button) findViewById(R.id.btn_search);

        Log.d(LOG_TAG, "Search Keyword: " + searchByTitle);

        searchBtn.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new movieDataAdapter(this, movieDataList);

        RecyclerView movieRecView = (RecyclerView) findViewById(R.id.movie_list_rv);
        movieRecView.setLayoutManager(layoutManager);
        movieRecView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(movieRecView.getContext(),
                layoutManager.getOrientation());

        movieRecView.addItemDecoration(dividerItemDecoration);
        movieRecView.setAdapter(adapter);

        //getDummyData(); // for recyclerView testing only
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_search) {
            search = (EditText) findViewById(R.id.searchEditText);
            searchByTitle = search.getText().toString().trim();

            Log.d(LOG_TAG, "Testing onClick, searchByTitle: " + searchByTitle);
            movieDataList.clear();
            fetchData(searchByTitle);
        }
    }

    /**
     * Function to fetch data from interment
     * @param title the search keyword, that basically part of the movie title
     */
    private void fetchData(String title) {

        //URL Key
        String URL = "https://api.themoviedb.org/3/search/movie?api_key=" + BuildConfig.API_KEY + "&include_adult=" + ADULTPARAM + "&query=" + title;

        Log.d(LOG_TAG, "Testing fetchData Running");


        // Basic URL for image
        final String imageURL = "http://image.tmdb.org/t/p/w92/";

        // Title/Label for Rating and Release Date:
        final String ratingLabel = "Rating: ";
        final String releaseDateLabel = "Release Date: ";

        AsyncHttpClient client = new AsyncHttpClient();

        // Check whether we are sending the right URL to the function
        Log.d(LOG_TAG, "Testing Get current movie data" + URL);

        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                // Check whether we successfully access onSuccess method
                Log.d(LOG_TAG, "Testing onSuccess");
                String result = new String(responseBody);
                try {
                    JSONObject results = new JSONObject(result);
                    JSONArray jsonArray = results.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String title = jsonArray.getJSONObject(i).getString("title");
                        String relDate = jsonArray.getJSONObject(i).getString("release_date");
                        String rate = jsonArray.getJSONObject(i).getString("vote_average");
                        String overview = jsonArray.getJSONObject(i).getString("overview");

                        String imageLinkextension = jsonArray.getJSONObject(i).getString("poster_path");

                        // Check whether we successfully fetch data from internet or not
                        Log.d(LOG_TAG, "Testing data ke " + i + " Title: " + title +
                                ", Rating: " + rate +
                                ", Release Date: " + relDate +
                                ", Image Link extension: " + imageLinkextension +
                                ", Synopsis: " + overview);

                        Double filmRateOrigin = (Double.parseDouble(rate)) * 10;

                        String filmRatePercent = ratingLabel + filmRateOrigin + " %";
                        String releaseDate = releaseDateLabel + relDate;
                        String imageLink = imageURL + imageLinkextension;

                        movieDataList.add(new movieData(title,
                                filmRatePercent,
                                releaseDate,
                                imageLink,
                                overview));
                        adapter.notifyDataSetChanged();

                        // check whether we successfully add the data to the arrayList
                        Log.d(LOG_TAG, "Testing data array ke " + i + " " + movieDataList.get(i).getTitle());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // check if we have a failure on getting data from internet
                Log.d(LOG_TAG, "Testing onFailure");
            }
        });
    }

    //Dummy Data --> only used to test the recycler view
    private void getDummyData() {
        movieDataList.add(new movieData("Jurrasic Park",
                "80%",
                "1993-06-03",
                "http://image.tmdb.org/t/p/w92//pU1ULUq8D3iRxl1fdX2lZIzdHuI.jpg"));
        movieDataList.add(new movieData("Spiderman",
                "72%",
                "2008-01-20",
                "http://image.tmdb.org/t/p/w92//lUPO9eYNlwqYVEstX8FQ5Sw0FKL.jpg"));
        movieDataList.add(new movieData("The Hacker",
                "60%",
                "2012-11-09",
                "http://image.tmdb.org/t/p/w92//dKx5lckYIQh0MpnsXxYbSkhASK2.jpg"));

        adapter.notifyDataSetChanged();
    }
}
