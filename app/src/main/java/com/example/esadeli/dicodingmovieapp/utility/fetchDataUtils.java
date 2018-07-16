package com.example.esadeli.dicodingmovieapp.utility;

import android.util.Log;

import com.example.esadeli.dicodingmovieapp.movieData;
import com.example.esadeli.dicodingmovieapp.movieDataAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by esadeli on 16/07/18.
 *
 * Utility class to fetch data from Internet
 */

public final class fetchDataUtils {

    private static final String LOG_TAG = fetchDataUtils.class.getSimpleName();

    public static void fetchData(String URL, final ArrayList<movieData> movieDataList, final movieDataAdapter adapter){

        Log.d(LOG_TAG,"testing Fetch Data");

        // Basic URL for image
        final String imageURL = "http://image.tmdb.org/t/p/w92/";

        // Title/Label for Rating and Release Date:
        final String ratingLabel = "Rating: ";
        final String releaseDateLabel = "Release Date: ";

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d(LOG_TAG,"Testing onSuccess");
                String result = new String(responseBody);
                try{
                    JSONObject results = new JSONObject(result);

                    JSONArray jsonArray = results.getJSONArray("results");

                    for(int i = 0; i<jsonArray.length();i++){
                        String title = jsonArray.getJSONObject(i).getString("title");
                        String relDate = jsonArray.getJSONObject(i).getString("release_date");
                        String rate = jsonArray.getJSONObject(i).getString("vote_average");
                        String overview = jsonArray.getJSONObject(i).getString("overview");

                        String imageLinkextension = jsonArray.getJSONObject(i).getString("poster_path");

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
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(LOG_TAG,"testing onFailure");
            }
        });
    }
}
