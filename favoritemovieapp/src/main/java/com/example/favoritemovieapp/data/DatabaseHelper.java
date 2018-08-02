package com.example.favoritemovieapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.favoritemovieapp.data.DatabaseContract.TABLE_NAME;


/**
 * Created by esadeli on 02/08/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "moviedb";

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE =
            String.format(
                    "CREATE TABLE %s"+
                            " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                            " %s TEXT NOT NULL,"+
                            " %s TEXT NOT NULL,"+
                            " %s TEXT NOT NULL,"+
                            " %s TEXT NOT NULL,"+
                            " %s TEXT NOT NULL)",
                    TABLE_NAME,
                    DatabaseContract.MovieColumns._ID,
                    DatabaseContract.MovieColumns.COLUMN_TITLE,
                    DatabaseContract.MovieColumns.COLUMN_RELEASE_DATE,
                    DatabaseContract.MovieColumns.COLUMN_IMAGE_LINK,
                    DatabaseContract.MovieColumns.COLUMN_RATING,
                    DatabaseContract.MovieColumns.COLUMN_OVERVIEW
            );

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}

