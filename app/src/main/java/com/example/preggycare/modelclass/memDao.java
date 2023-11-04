package com.example.preggycare.modelclass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface memDao {
   @Insert
    void insert(memmodel memmodel);

   @Query("SELECT EXISTS (SELECT * FROM membertable WHERE number =:number)")
    Boolean isPresent(String number);

   @Query("SELECT EXISTS (SELECT * FROM membertable WHERE number=:number AND password=:password)")
   Boolean login(String number,String password);
}
