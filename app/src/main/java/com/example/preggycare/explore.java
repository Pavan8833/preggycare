package com.example.preggycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class explore extends AppCompatActivity {
    TextView month1,month2,month3,month4,month5,month6,month7,month8,month9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_explore);
        month1 =findViewById(R.id.month1);
        month2 = findViewById(R.id.month2);
        month3 = findViewById(R.id.month3);
        month4 = findViewById(R.id.month4);
        month5 = findViewById(R.id.month5);
        month6 = findViewById(R.id.month6);
        month7 = findViewById(R.id.month7);
        month8 = findViewById(R.id.month8);
        month9 = findViewById(R.id.month9);




        month1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), month1.class);
                startActivity(intent);
            }


        });
        month2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), month2.class);
                startActivity(intent);
            }



        });
        month3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), month3.class);
                startActivity(intent);
            }


        });month4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), month4.class);
                startActivity(intent);
            }


        });month5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), month5.class);
                startActivity(intent);
            }


        });month6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), month6.class);
                startActivity(intent);
            }


        });month7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), month7.class);
                startActivity(intent);
            }


        });
        month8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), month8.class);
                startActivity(intent);
            }


        });
        month9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), month9.class);
                startActivity(intent);
            }


        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottomexplore);

        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.bottomexplore:
                    return true;
                case R.id.bottomhome:
                    startActivity(new Intent(getApplicationContext(),home.class));
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

    }
}