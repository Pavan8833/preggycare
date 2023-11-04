package com.example.preggycare.member;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.preggycare.R;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.adapters.patientadapter;
import com.example.preggycare.modelclass.patientmodel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class mempatients extends AppCompatActivity {

    RecyclerView patientrecycler;
    patientadapter patientadapter;
    SharedPreferences shp3;
    List<patientmodel> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mempatients);

        shp3 = getSharedPreferences("mypreferences",MODE_PRIVATE);
        String num = shp3.getString("hosid","");
        patientrecycler = findViewById(R.id.patientrecycler);

        patientrecycler.setLayoutManager(new LinearLayoutManager(this));
        list = (List<patientmodel>) Mydatabase.getInstance(getApplicationContext()).userprofiledao().getdata(num);
       if( list != null && !list.isEmpty()) {
           patientadapter = new patientadapter(list);
           patientrecycler.setAdapter(patientadapter);
       }else{
           Toast.makeText(this, "fill the details", Toast.LENGTH_SHORT).show();
       }












        BottomNavigationView bottomNavigationView = findViewById(R.id.membottomnavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottomprof);

        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.bottompatient:
                    return true;
                case R.id.bottomhome:
                    startActivity(new Intent(getApplicationContext(), memhome.class));
                    finish();
                    return true;
                case R.id.bottomdoctor:
                    startActivity(new Intent(getApplicationContext(), memdoctors.class));
                    finish();
                    return true;
                case R.id.bottomprof:
                    startActivity(new Intent(getApplicationContext(), memprofile.class));
                    finish();
                    return true;
            }
            return false;
        } );

    }
}