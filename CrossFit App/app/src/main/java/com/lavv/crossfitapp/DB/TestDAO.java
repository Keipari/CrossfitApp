package com.lavv.crossfitapp.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

@Dao
public interface TestDAO {
    @Query("SELECT * FROM Db")
    List<Db> getAll();

    @Insert
    void insert (Db test);

}
