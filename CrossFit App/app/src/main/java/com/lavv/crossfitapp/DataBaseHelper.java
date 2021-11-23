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


    //first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableMotion =
                "CREATE TABLE IF NOT EXISTS MOTION (" +
                        "ID_MOTION INTEGER PRIMARY KEY," +
                        "NAME VARCHAR(25) NOT NULL," +
                        "INFO TEXT NOT NULL );";

        String createTableSession = "" +
                "CREATE TABLE IF NOT EXISTS SESSION (" +
                    "ID_SESSION INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "DATE_SESSION DATETIME NOT NULL, " +
                    "COMMENT TEXT NOT NULL);";

        db.execSQL(createTableMotion);
        db.execSQL(createTableSession);

        String createTableSessionMotion =
                "CREATE TABLE IF NOT EXISTS SESSIONMOTION (" +
                        "ID_SESSION INTEGER, " +
                        "ID_MOTION INTEGER," +
                        "FOREIGN KEY (ID_SESSION) REFERENCES SESSION (ID_SESSION)," +
                        "FOREIGN KEY (ID_MOTION) REFERENCES SESSION (ID_MOTION));";

        db.execSQL(createTableSessionMotion);

        String createMotion = "INSERT INTO MOTION (ID_MOTION, NAME)" +
                "VALUES" +
                "(1, \"Air Squat\")," +
                "(2, \"Front Squat\")," +
                "(3, \"Overhead Squat\")," +
                "(4, \"Shoulder Press\")," +
                "(5, \"Push Press\")," +
                "(6, \"Push Jerk\")," +
                "(7, \"Deadlift\");";

        db.execSQL(createMotion);
    }

    //update a database
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) { }

    public void insertTable(SQLiteDatabase db, String table, String value){
        //db.insert(table, "" , "");
    }
}
