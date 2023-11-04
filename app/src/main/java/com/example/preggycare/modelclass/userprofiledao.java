package com.example.preggycare.modelclass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface userprofiledao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(userprofiletable userprofiletable);

    @Query("Select exists(select * from userprofiletable where mobilenum=:num )")
    Boolean search(String num);

    @Query("select username,mobilenum from userprofiletable where hosid=:hosid")
    List<patientmodel> getdata(String hosid);

    @Query("select count() from userprofiletable where hosid=:hosid")
    int getcount(String hosid);

    @Query("select * from userprofiletable where mobilenum=:num")
    userprofiletable getall(String num);


}
