/**
 * This class takes care of creating, opening, closing, and manage a database.
 * For this it inherits from SQLiteOpenHelper that gives us an easy way to handle these transactions.
 */
package com.lavv.crossfitapp.dblogic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DataBaseName = "crossfit.db";// name of the database

    public DataBaseHelper(@Nullable Context context) {
        super(context, DataBaseName, null , 1);
    }

    /**
     * This method is used to create the database, it is only called the first time the database is accessed
     * @param db SQLiteDatabase
     */
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

    /**
     * Here we make the insertion of a Session
     * @param session an Session object
     * @return a boolean statement, true if the insert was successful and false if it was not
     */
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

    /**
     *  This method makes the query and retrieve the information of all the existing sessions
     * @return a Session type list with all the registered sessions
     */
    public List<Session> getAllSessions(){
        List<Session> returnList = new ArrayList<>();

        // get data from the database
        String queryString = "SELECT * FROM SESSION ORDER BY ID_SESSION DESC;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            //loop into the results
            do{
                int id= cursor.getInt(0);
                String date= cursor.getString(1);
                String comment= cursor.getString(2);
                String movements= cursor.getString(3);

                Session session= new Session(id,date, comment,movements);

                returnList.add(session);
            }while(cursor.moveToNext());

        }else{
            // if there is no more rows. don't add anything
        }
        cursor.close();
        db.close();

        return returnList;
    }

    /**
     * This method makes the query to get all the information of an specific session given the id
     * @param id_s a String containing the id of a session
     * @return  a Session object
     */
    public Session getSessionById(String id_s){
        Session session;

        // get data from the database
        String queryString = "SELECT * FROM SESSION WHERE ID_SESSION="+id_s+";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            //loop into the results
            do{
                int id= cursor.getInt(0);
                String date= cursor.getString(1);
                String comment= cursor.getString(2);
                String movements= cursor.getString(3);

                session= new Session(id,date, comment,movements);

            }while(cursor.moveToNext());

        }else{
            // return session dummy
            session= new Session(-1,"null", "null","null");

        }
        cursor.close();
        db.close();

        return session;
    }

    /**
     * This method makes the query and retrieve the information of all the existing sessions made in the given month containing the given movement
     * @param month a String containing the number of the month
     * @param movement a String containing the name of a movement
     * @return a Session type list with the sessions of the given month containing the given movement
     */
    public List<Session> getSessionsByMonthMovements(String month, String movement){
        List<Session> returnList = new ArrayList<>();
        month="%-"+month+"-%";
        movement="%"+movement+"%";

        // get data from the database
        String queryString = "SELECT * FROM SESSION WHERE DATE_SESSION LIKE '"+month+"' AND MOVEMENTS LIKE '"+movement+"' ORDER BY ID_SESSION DESC;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            //loop into the results
            do{
                int id= cursor.getInt(0);
                String date= cursor.getString(1);
                String comment= cursor.getString(2);
                String movements= cursor.getString(3);

                Session session= new Session(id,date, comment,movements);

                returnList.add(session);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return returnList;
    }
}
