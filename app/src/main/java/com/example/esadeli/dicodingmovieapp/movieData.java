package com.example.esadeli.dicodingmovieapp;

/**
 * Created by esadeli on 04/07/18.
 *
 * POJO Class for simple and detailed movie data
 */
public class movieData {


    private String title;
    private String releaseDate;
    private String imageLink;
    private String rating;
    private String overview;

    public movieData(String title, String rating,
                     String releaseDate, String imageLink,  String overview) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.imageLink = imageLink;
        this.rating = rating;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }
}
