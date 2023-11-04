package com.example.preggycare.modelclass;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface memprofiledao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(memprofiletable memprofiletable);

    @Query("select exists(select * from memprofiletable where moblieno=:num)")
    Boolean search(String num);

    @Query("select * from memprofiletable where moblieno=:num")
    memprofiletable getall(String num);


}
