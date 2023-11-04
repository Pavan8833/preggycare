package com.example.preggycare.modelclass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface doctordao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(docterlist docterlist);

    @Query("select exists(select * from docterlist where hosid=:hosid )")
    Boolean search(String hosid);

    @Query("select count() from docterlist where hosid=:hosid")
    int getcount(String hosid);

    @Query("select * from docterlist where hosid=:hosid")
    List<docterlist> getall(String hosid);
}
