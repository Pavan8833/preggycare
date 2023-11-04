package com.example.preggycare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.modelclass.userDao;
import com.example.preggycare.modelclass.usermodel;
import com.google.android.material.tabs.TabLayout;


public class MainActivity5 extends AppCompatActivity {

    Mydatabase mydatabase;
    userDao userDao;

    Button logbtn, signup;
    EditText logusername, Password;

    SharedPreferences shp;
    SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        signup = findViewById(R.id.signup);
        logbtn = findViewById(R.id.logbtn);
        logusername = findViewById(R.id.logusername);
        Password = findViewById(R.id.logpassword);

         shp = getSharedPreferences("mypreferences",MODE_PRIVATE);
         edit = shp.edit();


        mydatabase = Mydatabase.getInstance(getApplicationContext());
        userDao=mydatabase.getDao();


        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = logusername.getText().toString().trim();
                String password = Password.getText().toString().trim();





                if( !username.equals("") && !password.equals("") ){
                    if( userDao.login(username,password)){
                        edit.putString("usernumber", username);
                        edit.apply();

                        startActivity(new Intent(MainActivity5.this,patientprofile.class));
                    }
                    else{
                        Toast.makeText(MainActivity5.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity5.this, "Enter the details", Toast.LENGTH_SHORT).show();
                }

            }
        });





        if (signup != null) {
            signup.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                startActivity(intent);
            });
        }
    }
}

