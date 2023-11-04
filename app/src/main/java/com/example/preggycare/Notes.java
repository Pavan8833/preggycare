package com.example.preggycare;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.preggycare.adapters.noteadapter;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.listners.NoteListner;
import com.example.preggycare.modelclass.usernote;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity implements NoteListner {


    private ActivityResultLauncher<Intent> launcher;
    FloatingActionButton notesbtn;
    RecyclerView notesrecycler;
    com.example.preggycare.adapters.noteadapter noteadapter;
    List<usernote> notelist;

    SharedPreferences shp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        shp3 = getSharedPreferences("mypreferences",MODE_PRIVATE);


        notesbtn = findViewById(R.id.notesbtn);
        notesrecycler = findViewById(R.id.notesrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        notesrecycler.setLayoutManager(layoutManager);

        notelist = new ArrayList<>();
        noteadapter = new noteadapter(notelist,  this);

        notesrecycler.setAdapter(noteadapter);

        notesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch(new Intent(getApplicationContext(),usernoteitem.class));

            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result-> {
                        if(result.getResultCode() == RESULT_OK){
                            getnotes();
                        }else{
                            Log.e(TAG, "Error: "+result.getResultCode() );
                        }
                });
        getnotes();
    }

    private void getnotes() {

        String num = shp3.getString("usernumber", "");

        @SuppressLint("StaticFieldLeak")
        class GetNotestask extends AsyncTask<Void, Void, List<usernote>> {

            @Override
            protected List<usernote> doInBackground(Void... voids) {
                return Mydatabase.getInstance(getApplicationContext()).usernotedao().getallnotes(num);
            }


            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(List<usernote> usernotes) {
                super.onPostExecute(usernotes);

                notelist.clear();
                notelist.addAll(usernotes);
                noteadapter.notifyDataSetChanged();

                notesrecycler.smoothScrollToPosition(0);
                notesrecycler.smoothScrollToPosition(0);


            }
        }

        new GetNotestask().execute();
    }

    @Override
    public void onNoteClicked(usernote usernote, int position) {
            Intent intent =  new Intent(getApplicationContext(),usernoteitem.class);
            intent.putExtra("isview",true);
            intent.putExtra("user",usernote);
            launcher.launch(intent);


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}