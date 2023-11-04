package com.example.preggycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.modelclass.docterlist;
import com.example.preggycare.modelclass.usermed;

public class doclist extends AppCompatActivity {

    EditText name, specific;
    Button back,done;
    docterlist doc;

    SharedPreferences shp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doclist);

        name = findViewById(R.id.nametxt);
        specific = findViewById(R.id.sptxt);
        back = findViewById(R.id.backbtn);
        done = findViewById(R.id.donebtn);

        shp = getSharedPreferences("mypreferences",MODE_PRIVATE);



        if (!getIntent().getBooleanExtra("isView",false)){
          doc = (docterlist) getIntent().getSerializableExtra("user");
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
                savedoc();
            }
        });

    }

    private void savedoc() {

        String num = shp.getString("hosid","");

        if(name.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "fill details", Toast.LENGTH_SHORT).show();
        }

        docterlist docterlist = new docterlist();
        docterlist.setName(name.getText().toString());
        docterlist.setSpecification(specific.getText().toString());

        if(doc != null){
            docterlist.setHosid(doc.getHosid());
            docterlist.setDocid(doc.getDocid());
        }else{
            docterlist.setHosid(num);
        }

        class savedoctask extends AsyncTask<Void,Void,Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Mydatabase.getInstance(getApplicationContext()).doctordao().insert(docterlist);
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
        new savedoctask().execute();

    }

    private void viewOrUpdateNote() {
        if (doc != null) {
            name.setText(doc.getName());
            specific.setText(doc.getSpecification());
        }else{
            Toast.makeText(this, "fill the details", Toast.LENGTH_SHORT).show();
        }

    }
}