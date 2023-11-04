package com.example.preggycare.member;

import androidx.appcompat.app.AppCompatActivity;

import com.example.preggycare.member.memsignup;
import com.example.preggycare.R;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.modelclass.memDao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class memlogin extends AppCompatActivity {


    EditText memloginnum,logpassword;
    Button submit,memsignup,btncheck;
    Mydatabase mydatabase;
    memDao memDao;

    SharedPreferences shs;
    SharedPreferences.Editor shedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memlogin);

        shs = getSharedPreferences("mypreferences",MODE_PRIVATE);
        shedit = shs.edit();

        mydatabase = Mydatabase.getInstance(getApplicationContext());
        memDao = mydatabase.memDao();

        memsignup = findViewById(R.id.memsignup);
        memloginnum = findViewById(R.id.memloginnum);
        logpassword = findViewById(R.id.logpassword);
        submit = findViewById(R.id.memloginbtn);
        btncheck = findViewById(R.id.btncheck);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = memloginnum.getText().toString().trim();
                String password = logpassword.getText().toString().trim();
                if(!username.equals("") && !password.equals("")){
                     if(memDao.login(username,password)){
                         shedit.putString("memnumber",username);
                         shedit.commit();

                         startActivity(new Intent(memlogin.this,memprofile.class));
                     }
                     else{
                         Toast.makeText(memlogin.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                     }
                }
                else{
                        Toast.makeText(memlogin.this, "fill the details", Toast.LENGTH_SHORT).show();
                    }


            }
        });


        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(memlogin.this,memprofile.class));
            }
        });

        memsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(memlogin.this, memsignup.class));
            }
        });


    }
}