package com.example.preggycare.modelclass;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface userpdfdao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(userpdf userpdf);
    @Query("select exists (select * from userpdf where usernum=:usernum)")
    Boolean search(String usernum);
    @Query("select * from userpdf where usernum=:usernum")
    LiveData<List<userpdf>> getallpdf(String usernum);

}
