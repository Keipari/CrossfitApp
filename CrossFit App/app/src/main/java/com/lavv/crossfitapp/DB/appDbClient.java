package com.lavv.crossfitapp.DB;

import android.content.Context;
import androidx.room.Room;

public class appDbClient {
    private Context cfContext;
    private static appDbClient myData;

    private appDb appDatabase;
    private appDbClient(Context cfContext){
        this.cfContext = cfContext;
        appDatabase = Room.databaseBuilder(cfContext, appDb.class,"Testdb").allowMainThreadQueries().build();
    }
    public static synchronized appDbClient getInstance(Context cfContext){
        if(myData == null) {
            myData = new appDbClient(cfContext);
        }
        return myData;
    }
    public appDb getAppDatabase(){
        return appDatabase;
    }
}
