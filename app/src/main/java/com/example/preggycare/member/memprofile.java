package com.example.preggycare.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.preggycare.R;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.modelclass.memprofiledao;
import com.example.preggycare.modelclass.memprofiletable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class memprofile extends AppCompatActivity {

    Mydatabase mydatabase;
    memprofiledao memprofiledao;

    SharedPreferences shp2;
    SharedPreferences.Editor shedit;


    ImageView memimage,memedit;
    EditText hospital,hospitalname,hospaddress,memname,memage,memnum,memenum,memaddress,memaadhar;

    Button memupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memprofile);

        mydatabase = Mydatabase.getInstance(getApplicationContext());
        memprofiledao = mydatabase.memprofiledao();

        shp2 = getSharedPreferences("mypreferences",MODE_PRIVATE);
        shedit = shp2.edit();
        String num = shp2.getString("memnumber","");


       memimage=findViewById(R.id.memimage);
       memedit = findViewById(R.id.memedit);
       memupdate = findViewById(R.id.memupdate);

        hospital = findViewById(R.id.hospitalid);
        hospitalname = findViewById(R.id.memhospitalname);
        hospaddress = findViewById(R.id.memhospitaladdress);
        memname = findViewById(R.id.memname);
        memage = findViewById(R.id.memage);
        memnum = findViewById(R.id.memnumber);
        memenum = findViewById(R.id.memextranumber);
        memaddress = findViewById(R.id.memaddress);
        memaadhar = findViewById(R.id.memaadhar);

        if(memprofiledao.search(num)){
            String hospitalid = memprofiledao.getall(num).getHosid();
            hospital.setText(String.valueOf(hospitalid));

            hospitalname.setText(memprofiledao.getall(num).getHosname());
            hospaddress.setText(memprofiledao.getall(num).getHosaddress());
            memname.setText(memprofiledao.getall(num).getMemname());
            memage.setText(memprofiledao.getall(num).getAge());
            memnum.setText(memprofiledao.getall(num).getMoblieno());
            memenum.setText(memprofiledao.getall(num).getEmergenno());
            memaddress.setText(memprofiledao.getall(num).getMemaddress());
            memaadhar.setText(memprofiledao.getall(num).getAadharnum());

        }else{
            Toast.makeText(this, "fill the details", Toast.LENGTH_SHORT).show();
        }


        memupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedetails();
                Toast.makeText(memprofile.this, "values updated", Toast.LENGTH_SHORT).show();
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.membottomnavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottomprof);

        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.bottomprof:
                    return true;
                case R.id.bottomhome:
                    startActivity(new Intent(getApplicationContext(), memhome.class));
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

    private void savedetails() {

        hospital.setText(hospital.getText().toString());
        hospitalname.setText(hospitalname.getText().toString());
        hospaddress.setText(hospaddress.getText().toString());
        memname.setText(memname.getText().toString());
        memage.setText(memage.getText().toString());
        memnum.setText(memnum.getText());
        memenum.setText(memenum.getText());
        memaadhar.setText(memaadhar.getText());
        memaddress.setText(memaddress.getText());

        String hospit = String.valueOf(hospital.getText());
        String hsname = hospitalname.getText().toString();
        String hosaddress = hospaddress.getText().toString();
        String mn = memname.getText().toString();
        String age =  String.valueOf(memage.getText());
        String mb = memnum.getText().toString();
        String mbn = memenum.getText().toString();
        String memadd = memaddress.getText().toString();
        String aad = memaadhar.getText().toString();

        shedit.putString("hosid",hospit);
        shedit.putString("hosname",hsname);
        shedit.commit();

        memprofiletable  memprofiletable = new memprofiletable(hospit,hsname,mn,hosaddress,age,mb,mbn,memadd,aad);


        class savedetailstask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                memprofiledao.insert(memprofiletable);
                return null;
            }
            @Override
            protected void onPostExecute(Void unused){
                super.onPostExecute(unused);

                Toast.makeText(memprofile.this, "details are added", Toast.LENGTH_SHORT).show();
            }

        }
        new savedetailstask().execute();

    }
}