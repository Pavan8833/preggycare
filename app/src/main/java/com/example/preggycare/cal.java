package com.example.preggycare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class cal extends AppCompatActivity {

    Button calbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        calbtn = findViewById(R.id.calbtn);

        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("content://com.android.calendar"));

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                boolean isIntentSafe = activities.size() > 0;

                if (isIntentSafe) {
                    // Start the activity if there's an app to handle it
                    startActivity(intent);
                } else {
                    // Handle the case where the user doesn't have the Google Calendar app installed
                    Toast.makeText(getApplicationContext(), "Google Calendar app not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottomcal);

        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.bottomcal:
                    return true;
                case R.id.bottomexplore:
                    startActivity(new Intent(getApplicationContext(),explore.class));
                    finish();
                    return true;
                case R.id.bottomhome:
                    startActivity(new Intent(getApplicationContext(),home.class));
                    finish();
                    return true;
                case R.id.bottomprof:
                    startActivity(new Intent(getApplicationContext(),patientprofile.class));
                    finish();
                    return true;
                case R.id.bottomfaq:
                    startActivity(new Intent(getApplicationContext(),faq.class));
                    finish();
                    return true;
            }
            return false;
        } );

    }
}