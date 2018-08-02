package com.example.favoritemovieapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static android.provider.BaseColumns._ID;

/**
 * Created by esadeli on 02/08/18.
 *
 */

public class MovieHelper {

    private static String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private Context context;

    public MovieHelper(Context context) {
        this.context = context;
    }

    //Utility method to open and close database
    public MovieHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return  this;
    }

    public MovieHelper openQuery() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    //CRUD Section


    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE,null,values);
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,null
                ,_ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }
    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID + " DESC");
    }

    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,_ID + " = ?", new String[]{id});
    }
}
