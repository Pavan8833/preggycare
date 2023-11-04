package com.example.preggycare.modelclass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface servicedao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(servicemodel servicemodel);

    @Query("select exists(select * from servicemodel where usernum =:usernum)")
    Boolean search(String usernum);

    @Query("select * from servicemodel where usernum=:usernum")
    List<servicemodel> getall(String usernum);
}
