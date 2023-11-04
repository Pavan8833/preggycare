package com.example.preggycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.modelclass.userprofiledao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
   ImageSlider imageSlider;
   ImageButton notes;
   ImageButton medicines;
   ImageButton files;
   ImageButton tests;

   ImageView userimage;
    Mydatabase mydatabase;
    userprofiledao userprofiledao;

    SharedPreferences shp1;

   TextView username, userhealthid, userdob, useraadhar, usernum,hosname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageSlider = findViewById(R.id.slider);

        userimage = findViewById(R.id.image);
        username = findViewById(R.id.name);
        userhealthid = findViewById(R.id.healthid);
        usernum = findViewById(R.id.num);
        useraadhar = findViewById(R.id.aadhar);
        userdob = findViewById(R.id.dob);
        hosname = findViewById(R.id.hosname);
        mydatabase = Mydatabase.getInstance(getApplicationContext());
        userprofiledao = mydatabase.userprofiledao();

        shp1 = getSharedPreferences("mypreferences",MODE_PRIVATE);
        String num = shp1.getString("usernumber", "");
        String nam = shp1.getString("hosname","");

        if( !num.isEmpty() && userprofiledao.search(num)){
        hosname.setText(nam);
        username.setText(userprofiledao.getall(num).getUsername());
        userhealthid.setText(String.valueOf( userprofiledao.getall(num).getPatientid()));
        userdob.setText(userprofiledao.getall(num).getDate());
        usernum.setText(num);
        useraadhar.setText(userprofiledao.getall(num).getAadharnum());
        }

//
//        if(name == null || healthid == null || aadhar == null|| dob == null|| num == null){
//            Log.d("Debug", "Name: " + name);
//            Log.d("Debug", "Health ID: " + healthid);
//            Toast.makeText(this, "fill the details in profile", Toast.LENGTH_SHORT).show();
//        }else{
//
//        }


        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.ambulance ,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.emergency ,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.doctor ,ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottomhome);

        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.bottomhome:
                    return true;
                case R.id.bottomexplore:
                    startActivity(new Intent(getApplicationContext(),explore.class));
                    finish();
                    return true;
                case R.id.bottomfaq:
                    startActivity(new Intent(getApplicationContext(),faq.class));
                    finish();
                    return true;
                case R.id.bottomprof:
                    startActivity(new Intent(getApplicationContext(),patientprofile.class));
                    finish();
                    return true;
                case R.id.bottomcal:
                    startActivity(new Intent(getApplicationContext(),cal.class));
                    finish();
                    return true;
            }
            return false;
        } );

        tests = findViewById(R.id.testsbutton);
        medicines = findViewById(R.id.medicinebutton);
        files = findViewById(R.id.photobutton);
        notes = findViewById(R.id.notesbutton);

        tests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),tests.class);
                startActivity(intent);
            }
        });
       medicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),medicines.class);
                startActivity(intent);
            }
        });
      files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),uploadphoto.class);
                startActivity(intent);
            }
        });
      notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Notes.class);
                startActivity(intent);
            }
        });


    }
}