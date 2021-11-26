package com.lavv.crossfitapp.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Db.class}, version = 1)
public abstract  class appDb extends RoomDatabase {
    public abstract Dbdao test();
}
