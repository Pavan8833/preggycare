package com.example.preggycare.modelclass;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "memprofiletable")
public class memprofiletable {
    @ColumnInfo(name = "hopitalid")
    String hosid;

    String hosname;
    String memname;

    String hosaddress;
    String age;

    @PrimaryKey(autoGenerate = false)
    @NonNull
    String moblieno;
    String emergenno;
    String memaddress;
    String aadharnum;

    public String getHosaddress() {
        return hosaddress;
    }

    public void setHosaddress(String hosaddress) {
        this.hosaddress = hosaddress;
    }

    public memprofiletable(String hosid, String hosname, String memname, String hosaddress,
                           String age, @NonNull String moblieno, String emergenno, String memaddress,
                           String aadharnum) {
        this.hosid = hosid;
        this.hosname = hosname;
        this.memname = memname;
        this.age = age;
        this.moblieno = moblieno;
        this.emergenno = emergenno;
        this.memaddress = memaddress;
        this.aadharnum = aadharnum;
        this.hosaddress = hosaddress;
    }

    public String getHosid() {
        return hosid;
    }

    public void setHosid(String hosid) {
        this.hosid = hosid;
    }

    public String getHosname() {
        return hosname;
    }

    public void setHosname(String hosname) {
        this.hosname = hosname;
    }


    public String getMemname() {
        return memname;
    }

    public void setMemname(String memname) {
        this.memname = memname;
    }

    public String  getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMoblieno() {
        return moblieno;
    }

    public void setMoblieno(String moblieno) {
        this.moblieno = moblieno;
    }

    public String getEmergenno() {
        return emergenno;
    }

    public void setEmergenno( String emergenno) {
        this.emergenno = emergenno;
    }

    public String getMemaddress() {
        return memaddress;
    }

    public void setMemaddress(String memaddress) {
        this.memaddress = memaddress;
    }

    public String getAadharnum() {
        return aadharnum;
    }

    public void setAadharnum(String aadharnum) {
        this.aadharnum = aadharnum;
    }
}

