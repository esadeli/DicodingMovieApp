package com.example.esadeli.dicodingmovieapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.esadeli.dicodingmovieapp.data.DatabaseContract.TABLE_NAME;
import static com.example.esadeli.dicodingmovieapp.data.DatabaseContract.*;
/**
 * Created by esadeli on 01/08/18.
 *
 * Class extension of SQLiteOpenHelper for create and upgrade the database
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
                    MovieColumns._ID,
                    MovieColumns.COLUMN_TITLE,
                    MovieColumns.COLUMN_RELEASE_DATE,
                    MovieColumns.COLUMN_IMAGE_LINK,
                    MovieColumns.COLUMN_RATING,
                    MovieColumns.COLUMN_OVERVIEW
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
