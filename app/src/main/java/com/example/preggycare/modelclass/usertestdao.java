package com.example.preggycare.modelclass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface usertestdao {

    @Insert
    public void insert(usertest usertest);

    @Query("select  * from usertest")
    List<usertest> getall( );

    @Query("select  * from usertest where mobilenum=:mobilenum")
    List<usertest> getalltests(String mobilenum );
}
