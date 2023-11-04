package com.example.preggycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preggycare.R;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.modelclass.usermed;
import com.example.preggycare.modelclass.usertest;

public class usertestitem extends AppCompatActivity {


    EditText testnote;
    Button back,done;
    usertest alreadynote;

    SharedPreferences shp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usertestitem);

        testnote = findViewById(R.id.testnote);
        back = findViewById(R.id.backbtn);
        done = findViewById(R.id.donebtn);

        shp = getSharedPreferences("mypreferences",MODE_PRIVATE);

        if(!getIntent().getBooleanExtra("isView",false)){
            alreadynote = (usertest) getIntent().getSerializableExtra("user");
            viewOrupdateNote();
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savetestnote();
            }


        });

    }

    private void savetestnote() {

        String num =  shp.getString("usernumber","");

        if(testnote.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "write the tests", Toast.LENGTH_SHORT).show();
        }

        usertest usertest =  new usertest();
        usertest.setTestname(testnote.getText().toString());


        if (alreadynote != null) {
            usertest.setMobilenum(alreadynote.getMobilenum());
            usertest.setId(alreadynote.getId());
        }else{
            usertest.setMobilenum(num);
        }

        class savetesttask extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Mydatabase.getInstance(getApplicationContext()).usertestdao().insert(usertest);
                return null;
            }
            @Override
            protected void onPostExecute(Void unused){
                super.onPostExecute(unused);
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();

            }

        }
        new savetesttask().execute();


    }

    private void viewOrupdateNote() {
        if(alreadynote != null){
        testnote.setText(alreadynote.getTestname());
    }else{
            Toast.makeText(this, "fill the details", Toast.LENGTH_SHORT).show();
        }
}}