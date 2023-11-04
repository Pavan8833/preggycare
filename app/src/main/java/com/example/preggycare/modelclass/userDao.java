package com.example.preggycare.modelclass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface userDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(usermodel usermodel);
   @Query("SELECT EXISTS(SELECT * FROM usertable WHERE number=:number)")
   Boolean isPresent(String number);
   @Query("SELECT EXISTS (SELECT * FROM usertable WHERE number=:number AND password=:password)" )
   Boolean login(String number,String password);

}
