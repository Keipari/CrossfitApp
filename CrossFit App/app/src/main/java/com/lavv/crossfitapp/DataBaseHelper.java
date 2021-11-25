package com.lavv.crossfitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DataBaseName = "crossfit.db";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DataBaseName, null , 1);
    }


    //This method is used to create the database, it is only called the first time the database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {


        String createTableSession = "" +
                "CREATE TABLE IF NOT EXISTS SESSION (" +
                    "ID_SESSION INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "DATE_SESSION DATETIME NOT NULL, " +
                    "COMMENT TEXT NOT NULL," +
                    "MOVEMENTS TEXT NOT NULL);";

        db.execSQL(createTableSession);

    }

    //update a database
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) { }



    public boolean addSession(Session session){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("DATE_SESSION", session.getDate_session());
        cv.put("COMMENT", session.getComment());
        cv.put("MOVEMENTS", session.getMovements());

        long insert = db.insert("SESSION",null,cv);

        if( insert==-1){
            return false;
        }else{
            return true;
        }
    }


    public void insertTable(SQLiteDatabase db, String table, String value){
        //db.insert(table, "" , "");
    }
}
