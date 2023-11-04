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

import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.modelclass.servicemodel;
import com.example.preggycare.modelclass.usermed;

public class serviceitem extends AppCompatActivity {

    EditText sernote;
    Button back,done;
    servicemodel alreadynote;

    SharedPreferences shp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceitem);

        sernote = findViewById(R.id.textservice);
        back = findViewById(R.id.backbtn);
        done = findViewById(R.id.donebtn);

        shp = getSharedPreferences("mypreferences",MODE_PRIVATE);

        if (getIntent().getBooleanExtra("isView",false)){
            alreadynote = (servicemodel) getIntent().getSerializableExtra("user");
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
                saveservicenote();
            }
        });


    }

    private void saveservicenote() {
        String num = shp.getString("memnumber", "");

        if (!num.equals("")) {

            if (sernote.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "write the services", Toast.LENGTH_SHORT).show();
            }

            servicemodel servicemodel = new servicemodel();
            servicemodel.setServices(sernote.getText().toString());


            if (alreadynote != null) {
                servicemodel.setUsernum(alreadynote.getUsernum());
                servicemodel.setId(alreadynote.getId());
            } else {
                servicemodel.setUsernum(num);
            }


            class savenotetask extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {

                    Mydatabase.getInstance(getApplicationContext()).servicedao().insert(servicemodel);
                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();

                }
            }

            new savenotetask().execute();
        }
    }

    private void viewOrUpdateNote() {
        sernote.setText(alreadynote.getServices());
    }
}