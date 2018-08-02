package com.example.esadeli.dicodingmovieapp.data;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URI;

/**
 * Created by esadeli on 01/08/18.
 *
 * Constant for table name and uri
 */

public class DatabaseContract {

    //Table Name
    public static final String TABLE_NAME = "moviefavorit";

    //URI Authority
    public static final String AUTHORITY = "com.example.esadeli.dicodingmovieapp";
    public static final String SCHEME = "content";

    private DatabaseContract() {
    }

    public static final class MovieColumns implements BaseColumns{
        public static String COLUMN_TITLE = "title";
        public static String COLUMN_RELEASE_DATE = "releasedate";
        public static String COLUMN_IMAGE_LINK = "imagelink";
        public static String COLUMN_RATING = "rating";
        public static String COLUMN_OVERVIEW = "overview";

        public static Uri CONTENT_URI =
                        new Uri.Builder()
                                .scheme(SCHEME)
                                .authority(AUTHORITY)
                                .appendPath(TABLE_NAME)
                                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName){
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName){
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
}
