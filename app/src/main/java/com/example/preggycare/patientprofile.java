package com.example.preggycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.modelclass.userprofiledao;
import com.example.preggycare.modelclass.userprofiletable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class patientprofile extends AppCompatActivity {

    ImageView userimage,userediticon;
    EditText health,username,dob,userage,usernum,useraddress,useraadhar,gesage,userweight,userheight,userbmi;
    EditText noofbabies,userbg,userrh,hospitalid,userdoctor,admitdate,dischargedate,relativename,relativenum;

    EditText healthcond;
    CheckBox userbaby;
    Button update;

    Mydatabase mydatabase;
    userprofiledao userprofiledao;

    SharedPreferences shp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientprofile);

        shp1 = getSharedPreferences("mypreferences",MODE_PRIVATE);
        String num = shp1.getString("usernumber", "");

        mydatabase = Mydatabase.getInstance(getApplicationContext());
        userprofiledao = mydatabase.userprofiledao();

        userimage = findViewById(R.id.userimage);
        userediticon = findViewById(R.id.userediticon);
        userbaby = findViewById(R.id.userbaby);

        health = findViewById(R.id.healthid);
        username = findViewById(R.id.username);
        dob = findViewById(R.id.dob);
        userage = findViewById(R.id.userage);
        usernum = findViewById(R.id.usernumber);
        useraddress = findViewById(R.id.useraddress);
        useraadhar =  findViewById(R.id.useraadhar);
        gesage = findViewById(R.id.gestationalage);
        userweight = findViewById(R.id.userweight);
        userheight = findViewById(R.id.userheight);
        userbmi = findViewById(R.id.userbmi);
        noofbabies = findViewById(R.id.noofbabies);
        userbg = findViewById(R.id.userbloodgroup);
        userrh = findViewById(R.id.userrh);
        hospitalid = findViewById(R.id.hospitalid);
        userdoctor = findViewById(R.id.userdoctor);
        admitdate = findViewById(R.id.admitdate);
        dischargedate = findViewById(R.id.dischargedate);
        relativename = findViewById(R.id.useremername);
        relativenum = findViewById(R.id.useremernum);
        update = findViewById(R.id.userupdate);
        healthcond = findViewById(R.id.healthcon);




        if( !num.isEmpty() && userprofiledao.search(num)){

            int healthid = userprofiledao.getall(num).getPatientid();
            health.setText(String.valueOf(healthid));

            username.setText(userprofiledao.getall(num).getUsername());
            dob.setText(userprofiledao.getall(num).getDate());
            userage.setText(userprofiledao.getall(num).getAge());
            usernum.setText(num);
            useraddress.setText(userprofiledao.getall(num).getAddress());
            useraadhar.setText(userprofiledao.getall(num).getAadharnum());
            gesage.setText(userprofiledao.getall(num).getGestationalage());
            userweight.setText(userprofiledao.getall(num).getWeight());
            userheight.setText(userprofiledao.getall(num).getHeight());
            userbmi.setText(userprofiledao.getall(num).getBmi());
            noofbabies.setText(userprofiledao.getall(num).getBabycount());
            userbg.setText(userprofiledao.getall(num).getBloodgroup());
            userrh.setText(userprofiledao.getall(num).getRhfactor());
            hospitalid.setText(userprofiledao.getall(num).getHosid());
            userdoctor.setText(userprofiledao.getall(num).getDocname());
            admitdate.setText( userprofiledao.getall(num).getAdmit());
            dischargedate.setText( userprofiledao.getall(num).getDischarge());
            relativename.setText(userprofiledao.getall(num).getEmername());
            relativenum.setText(userprofiledao.getall(num).getEmernum());
            healthcond.setText(userprofiledao.getall(num).getHealthcond());

        }else{
            Toast.makeText(this, "Enter the details", Toast.LENGTH_SHORT).show();
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SaveuserdetailsTask().execute();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottomprof);

        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.bottomprof:
                    return true;
                case R.id.bottomexplore:
                    startActivity(new Intent(getApplicationContext(),explore.class));
                    finish();
                    return true;
                case R.id.bottomfaq:
                    startActivity(new Intent(getApplicationContext(),faq.class));
                    finish();
                    return true;
                case R.id.bottomhome:
                    startActivity(new Intent(getApplicationContext(),home.class));
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

    private void savedetails() {

        String num = shp1.getString("usernumber", "");

        int heal = Integer.parseInt(String.valueOf(health.getText()));
        String un = username.getText().toString();
        String d = String.valueOf(dob.getText());
        String age = String.valueOf(userage.getText());
        String nu = num;
        String aa = useraadhar.getText().toString();
        String add = useraddress.getText().toString();
        String g = String.valueOf(gesage.getText());
        String wei = String.valueOf(userweight.getText());
        String high = String.valueOf(userheight.getText());
        String bmi = String.valueOf(userbmi.getText());
        String bc = String.valueOf(noofbabies.getText());
        String b = String.valueOf(userbg.getText());
        String rh = String.valueOf(userrh.getText());
        String healcon = healthcond.getText().toString();
        String hosid = hospitalid.getText().toString();
        String doc = userdoctor.getText().toString();
        String admit = admitdate.getText().toString();
        String discharge = dischargedate.getText().toString();
        String emername = relativename.getText().toString();
        String emernum = relativenum.getText().toString();

        userprofiletable user = new userprofiletable(heal,un,d,age,nu,aa,add,g,wei,high,bmi,bc,b,rh,healcon,hosid,doc,admit,discharge,emername,emernum);
        userprofiledao.insert(user);

    }

    private class SaveuserdetailsTask extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... voids) {
            savedetails();
            return null;
        }
        @Override
        protected void onPostExecute(Void unused){
            super.onPostExecute(unused);


            health.setText(health.getText().toString());
            username.setText(username.getText().toString());
            dob.setText(dob.getText().toString());
            userage.setText(userage.getText().toString());
            usernum.setText(usernum.getText().toString());
            useraddress.setText(useraddress.getText().toString());
            useraadhar.setText(useraadhar.getText().toString());
            gesage.setText(gesage.getText().toString());
            userweight.setText(userweight.getText().toString());
            userheight.setText(userheight.getText().toString());
            userbmi.setText(userbmi.getText().toString());
            noofbabies.setText(noofbabies.getText().toString());
            userbg.setText(userbg.getText().toString());
            userrh.setText(userrh.getText().toString());
            hospitalid.setText(hospitalid.getText().toString());
            userdoctor.setText(userdoctor.getText().toString());
            admitdate.setText(admitdate.getText().toString());
            dischargedate.setText(dischargedate.getText().toString());
            relativename.setText(relativename.getText().toString());
            relativenum.setText(relativenum.getText().toString());




        }

    }




}