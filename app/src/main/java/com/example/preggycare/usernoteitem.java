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
import com.example.preggycare.modelclass.usernote;

public class usernoteitem extends AppCompatActivity {

    EditText texttitle,textnote;
    Button back,done;
    usernote alreadynote;

    SharedPreferences shp ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usernoteitem);
        texttitle = findViewById(R.id.texttitle);
        textnote = findViewById(R.id.textnote);
        back = findViewById(R.id.backbtn);
        done = findViewById(R.id.donebtn);

        shp = getSharedPreferences("mypreferences",MODE_PRIVATE);



        if (!getIntent().getBooleanExtra("isView",false)){
            alreadynote = (usernote) getIntent().getSerializableExtra("user");
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
                savenote();
            }
        });
    }

    private void savenote() {

        String num = shp.getString("usernumber", "");
        if (!num.equals("")) {
            if (texttitle.getText().toString().trim().isEmpty() &&
                    textnote.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "write the note", Toast.LENGTH_SHORT).show();
                return;
            }
            usernote usernote = new usernote();
            usernote.setTitle(texttitle.getText().toString());
            usernote.setNotes(textnote.getText().toString());


            if (alreadynote != null) {
                usernote.setMobilenum(alreadynote.getMobilenum());
                usernote.setId(alreadynote.getId());
            } else {
                usernote.setMobilenum(num);
            }

            class savenotetask extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    Mydatabase.getInstance(getApplicationContext()).usernotedao().insert(usernote);
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
        if (alreadynote != null) {
            textnote.setText(alreadynote.getNotes());
            texttitle.setText(alreadynote.getTitle());
        }else {
            Toast.makeText(this, "fill the details", Toast.LENGTH_SHORT).show();
        }

    }
}