package com.example.preggycare.modelclass;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "membertable")
public class memmodel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int id ;
    String number;
    String password;

    public memmodel(int id, String number, String password) {
        this.id = id;
        this.number = number;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

