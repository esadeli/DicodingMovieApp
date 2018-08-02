package com.example.favoritemovieapp.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.favoritemovieapp.data.DatabaseContract;

import static android.provider.BaseColumns._ID;
import static com.example.favoritemovieapp.data.DatabaseContract.getColumnInt;
import static com.example.favoritemovieapp.data.DatabaseContract.getColumnString;

/**
 * Created by esadeli on 02/08/18.
 */

public class MovieDataCursor implements Parcelable {

    private int id;
    private String titleCursor;
    private String releaseDateCursor;
    private String imageLinkCursor;
    private String ratingCursor;
    private String overviewCursor;

    public MovieDataCursor() {
    }

    protected MovieDataCursor(Parcel in){

        this.id = in.readInt();
        this.titleCursor = in.readString();
        this.releaseDateCursor = in.readString();
        this.ratingCursor = in.readString();
        this.overviewCursor = in.readString();
        this.imageLinkCursor = in.readString();
    }

    public MovieDataCursor(Cursor cursor){
        this.id = getColumnInt(cursor, _ID);
        this.titleCursor =
                getColumnString(cursor, DatabaseContract.MovieColumns.COLUMN_TITLE);
        this.releaseDateCursor =
                getColumnString(cursor, DatabaseContract.MovieColumns.COLUMN_RELEASE_DATE);
        this.ratingCursor =
                getColumnString(cursor, DatabaseContract.MovieColumns.COLUMN_RATING);
        this.overviewCursor =
                getColumnString(cursor, DatabaseContract.MovieColumns.COLUMN_OVERVIEW);
        this.imageLinkCursor =
                getColumnString(cursor,DatabaseContract.MovieColumns.COLUMN_IMAGE_LINK);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleCursor() {
        return titleCursor;
    }

    public void setTitleCursor(String titleCursor) {
        this.titleCursor = titleCursor;
    }

    public String getReleaseDateCursor() {
        return releaseDateCursor;
    }

    public void setReleaseDateCursor(String releaseDateCursor) {
        this.releaseDateCursor = releaseDateCursor;
    }

    public String getImageLinkCursor() {
        return imageLinkCursor;
    }

    public void setImageLinkCursor(String imageLinkCursor) {
        this.imageLinkCursor = imageLinkCursor;
    }

    public String getRatingCursor() {
        return ratingCursor;
    }

    public void setRatingCursor(String ratingCursor) {
        this.ratingCursor = ratingCursor;
    }

    public String getOverviewCursor() {
        return overviewCursor;
    }

    public void setOverviewCursor(String overviewCursor) {
        this.overviewCursor = overviewCursor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<MovieDataCursor> CREATOR =
            new Parcelable.Creator<MovieDataCursor>(){
                @Override
                public MovieDataCursor createFromParcel(Parcel source) {
                    return new MovieDataCursor(source);
                }

                @Override
                public MovieDataCursor[] newArray(int size) {
                    return new MovieDataCursor[size];
                }
            };
}

