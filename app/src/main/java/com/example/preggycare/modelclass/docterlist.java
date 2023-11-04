package com.example.preggycare.modelclass;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class docterlist implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int docid;

    public int  getDocid() {
        return docid;
    }

    public void setDocid(int  docid) {
        this.docid = docid;
    }

    String hosid;

    String name;

    String specification;


    public String getName() {
        return name;
    }

    public String getHosid() {
        return hosid;
    }

    public void setHosid(String hosid) {
        this.hosid = hosid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}
