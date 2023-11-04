package com.example.preggycare.member;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.preggycare.R;
import com.example.preggycare.modelclass.Mydatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class memhome extends AppCompatActivity {

    TextView dc,pc;

    TextView service;

    SharedPreferences shp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memhome);
        BottomNavigationView bottomNavigationView = findViewById(R.id.membottomnavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottomprof);

        shp = getSharedPreferences("mypreferences",MODE_PRIVATE);
        String num = shp.getString("hosid","");
        String number  = shp.getString("usernumber","");

        dc = findViewById(R.id.doctorcount);
        pc = findViewById(R.id.patientcount);
        service = findViewById(R.id.services);

        if(Mydatabase.getInstance(getApplicationContext()).userprofiledao().search(number)) {
            dc.setText(String.valueOf(Mydatabase.getInstance(getApplicationContext()).doctordao().getcount(num)));
            pc.setText(String.valueOf(Mydatabase.getInstance(getApplicationContext()).userprofiledao().getcount(num)));
        }else{
            Toast.makeText(this, "fill the details", Toast.LENGTH_SHORT).show();
        }
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(memhome.this,memservices.class));
            }
        });


        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.bottomhome:
                    return true;
                case R.id.bottomprof:
                    startActivity(new Intent(getApplicationContext(), memprofile.class));
                    finish();
                    return true;
                case R.id.bottomdoctor:
                    startActivity(new Intent(getApplicationContext(), memdoctors.class));
                    finish();
                    return true;
                case R.id.bottompatient:
                    startActivity(new Intent(getApplicationContext(), mempatients.class));
                    finish();
                    return true;
            }
            return false;
        } );

    }
}