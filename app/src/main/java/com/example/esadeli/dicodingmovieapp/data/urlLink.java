package com.example.esadeli.dicodingmovieapp.data;

import com.example.esadeli.dicodingmovieapp.BuildConfig;

/**
 * Created by esadeli on 16/07/18.
 *
 * Data class to store URL information
 */

public final class urlLink {

    private static final String ADULTPARAM = "false";

    private static final String urlSearch =
            "https://api.themoviedb.org/3/search/movie?api_key="+ BuildConfig.API_KEY
                    +"&include_adult="+ADULTPARAM+"&query=";

    public static final String urlNOW = "https://api.themoviedb.org/3/movie/now_playing?api_key="+BuildConfig.API_KEY+"&language=en-US";

    public static final String urlUpcoming = "https://api.themoviedb.org/3/movie/upcoming?api_key="+BuildConfig.API_KEY+"&language=en-US";

    //function to form urlSearch
    public static String formUrlSearch(String title){

        return urlSearch+title;
    }

}
