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
import com.example.preggycare.modelclass.usernote;

public class usermeditem extends AppCompatActivity {

    EditText mednote;
    Button back,done;
    usermed alreadynote;

    SharedPreferences shp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermeditem);

        mednote = findViewById(R.id.mednote);
        back = findViewById(R.id.backbtn);
        done = findViewById(R.id.donebtn);

        shp = getSharedPreferences("mypreferences",MODE_PRIVATE);

        if (getIntent().getBooleanExtra("isView",false)){
            alreadynote = (usermed) getIntent().getSerializableExtra("user");
            viewOrUpdateNote();
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
                savemednote();
            }
        });
    }

    private void savemednote() {

        String num =  shp.getString("usernumber","");

        if(mednote.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "write the medicines", Toast.LENGTH_SHORT).show();
        }

        usermed usermed =  new usermed();
        usermed.setMedname(mednote.getText().toString());


        if (alreadynote != null) {
            usermed.setMobilenum(alreadynote.getMobilenum());
            usermed.setId(alreadynote.getId());
        }else{
            usermed.setMobilenum(num);
        }

        class savenotetask extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Mydatabase.getInstance(getApplicationContext()).usermeddao().insert(usermed);
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
        new savenotetask().execute();


    }

    private void viewOrUpdateNote() {
        if(alreadynote != null){
            mednote.setText(alreadynote.getMedname());
        }else{
            Toast.makeText(this, "Fill the details", Toast.LENGTH_SHORT).show();
        }

        
    }
}