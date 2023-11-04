package com.example.preggycare.modelclass;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {memmodel.class,usermodel.class,usernote.class,usermed.class,userprofiletable.class,memprofiletable.class,usertest.class,docterlist.class,userpdf.class,servicemodel.class},version=1)
public abstract class Mydatabase extends RoomDatabase {

    private static final String DB_NAME= "preggydb";
    public abstract userDao getDao();
    public abstract memDao memDao();

    public abstract userprofiledao userprofiledao();

    public abstract  memprofiledao memprofiledao();
    public abstract usermeddao usermeddao();
    public abstract usernotedao usernotedao();

    public abstract usertestdao usertestdao();

    public abstract doctordao doctordao();

    public abstract userpdfdao userpdfdao();

    public abstract servicedao servicedao();

    private static Mydatabase mydatabase;

    public static synchronized Mydatabase getInstance(Context context){
        if(mydatabase == null) {
            mydatabase = Room.databaseBuilder(context, Mydatabase.class, DB_NAME).
                    allowMainThreadQueries().fallbackToDestructiveMigration().
                    build();
        }
            return mydatabase;
        }
    }



