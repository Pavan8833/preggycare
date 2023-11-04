package com.example.preggycare.modelclass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "userprofiletable")
public class userprofiletable implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "healthid")
    int patientid;
    String username;
    String date;
    String age;
    String mobilenum;

    String address;
    String aadharnum;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    byte[] image;
    String gestationalage;
    String weight;
    String height;
    String bmi;

    String  babycount;
    String  bloodgroup;
    String  rhfactor;
    String healthcond;

    String hosid;
    String docname;
    String admit;
    String discharge;

    String emername;
    String emernum;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobilenum() {
        return mobilenum;
    }

    public void setMobilenum(String mobilenum) {
        this.mobilenum = mobilenum;
    }


    public String getAadharnum() {
        return aadharnum;
    }

    public void setAadharnum(String aadharnum) {
        this.aadharnum = aadharnum;
    }

    public String getGestationalage() {
        return gestationalage;
    }

    public void setGestationalage(String gestationalage) {
        this.gestationalage = gestationalage;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getBabycount() {
        return babycount;
    }

    public void setBabycount(String babycount) {
        this.babycount = babycount;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getRhfactor() {
        return rhfactor;
    }

    public void setRhfactor(String rhfactor) {
        this.rhfactor = rhfactor;
    }

    public String getHealthcond() {
        return healthcond;
    }

    public void setHealthcond(String healthcond) {
        this.healthcond = healthcond;
    }

    public String getHosid() {
        return hosid;
    }

    public void setHosid(String hosid) {
        this.hosid = hosid;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getAdmit() {
        return admit;
    }

    public void setAdmit(String admit) {
        this.admit = admit;
    }

    public String getDischarge() {
        return discharge;
    }

    public void setDischarge(String discharge) {
        this.discharge = discharge;
    }

    public String getEmername() {
        return emername;
    }

    public void setEmername(String emername) {
        this.emername = emername;
    }

    public String getEmernum() {
        return emernum;
    }

    public void setEmernum(String emernum) {
        this.emernum = emernum;
    }


//

    public  userprofiletable(int patientid, String username, String date, String age, String mobilenum, String aadharnum, String address,
                             String gestationalage, String weight,String height, String bmi,String babycount,
                             String bloodgroup, String rhfactor, String healthcond, String hosid, String docname,
                                String admit, String discharge, String emername, String emernum) {
        this.patientid = patientid;
        this.username = username;
        this.date = date;
        this.age = age;
        this.mobilenum = mobilenum;
        this.aadharnum = aadharnum;
        this.address = address;
        this.gestationalage = gestationalage;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.babycount = babycount;
        this.bloodgroup = bloodgroup;
        this.rhfactor = rhfactor;
        this.healthcond = healthcond;
        this.hosid = hosid;
        this.docname = docname;
        this.admit = admit;
        this.discharge = discharge;
        this.emername = emername;
        this.emernum = emernum;
    }
}

