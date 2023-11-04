package com.example.preggycare.modelclass;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface usernotedao {

    @Insert
    void insert(usernote usernote);

    @Query("select * from usernote where mobilenum=:mobilenum")
    List<usernote> getallnotes(String mobilenum);
}
