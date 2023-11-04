package com.example.preggycare.modelclass;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class usernote implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
     int id ;

    String mobilenum;
    String title;

    public int getId() {
        return id;
    }

    public String getMobilenum() {
        return mobilenum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMobilenum(String mobilenum) {
        this.mobilenum = mobilenum;
    }

    String notes;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
