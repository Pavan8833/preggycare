package com.example.preggycare.modelclass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface usermeddao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(usermed usermed);

    @Query("select * from usermed where  mobilenum=:mobilenum")
    List<usermed> getallmeds( String  mobilenum);

    @Query("select * from usermed")
    List<usermed> getmeds();
}
