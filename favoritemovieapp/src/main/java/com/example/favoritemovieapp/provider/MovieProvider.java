package com.example.favoritemovieapp.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.favoritemovieapp.data.MovieHelper;

import static com.example.favoritemovieapp.data.DatabaseContract.AUTHORITY;
import static com.example.favoritemovieapp.data.DatabaseContract.MovieColumns.CONTENT_URI;
import static com.example.favoritemovieapp.data.DatabaseContract.TABLE_NAME;

/**
 * Created by esadeli on 02/08/18.
 *
 */

public class MovieProvider extends ContentProvider {

    //Constant to differentiate URI address
    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //Uri matcher
    static {

        // content://com.dicoding.mynotesapp/note
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, MOVIE);

        // content://com.dicoding.mynotesapp/note/id
        sUriMatcher.addURI(AUTHORITY,
                TABLE_NAME + "/#",
                MOVIE_ID);
    }

    private MovieHelper movieHelper;

    @Override
    public boolean onCreate() {
        movieHelper = new MovieHelper(getContext());
        movieHelper.open();
        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long itemAdded;

        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                itemAdded = movieHelper.insertProvider(values);
                break;
            default:
                itemAdded = 0;
                break;
        }

        if (itemAdded > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + itemAdded);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                cursor = movieHelper.queryProvider();
                break;
            case MOVIE_ID:
                cursor = movieHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }


    //Unused update method
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleteItem;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                deleteItem = movieHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleteItem = 0;
                break;
        }

        if (deleteItem > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleteItem;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}

