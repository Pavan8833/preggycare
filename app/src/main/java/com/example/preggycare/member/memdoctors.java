package com.example.preggycare.member;

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

import com.example.preggycare.R;
import com.example.preggycare.doclist;
import com.example.preggycare.listners.doclistener;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.adapters.docadapter;
import com.example.preggycare.modelclass.docterlist;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class memdoctors extends AppCompatActivity implements  doclistener {

    RecyclerView docrecycler;

    FloatingActionButton docbtn;

    com.example.preggycare.adapters.docadapter docadapter;

    private ActivityResultLauncher<Intent> launcher;

    List<docterlist> doclis;

    SharedPreferences shp3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memdoctors);

        shp3 = getSharedPreferences("mypreferences",MODE_PRIVATE);


     docbtn  = findViewById(R.id.fbutton);
     docrecycler = findViewById(R.id.docrecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        docrecycler.setLayoutManager(layoutManager);
        doclis = new ArrayList<>();
        docadapter = new docadapter(doclis, this);
        docrecycler.setAdapter(docadapter);

        docbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch(new Intent(getApplicationContext(), doclist.class));
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        getdoc();
                    }else{
                        Log.e(TAG,"Error :"+result.getResultCode());
                    }
                });

        getdoc();



        BottomNavigationView bottomNavigationView = findViewById(R.id.membottomnavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottomprof);

        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.bottomdoctor:
                    return true;
                case R.id.bottomhome:
                    startActivity(new Intent(getApplicationContext(), memhome.class));
                    finish();
                    return true;
                case R.id.bottomprof:
                    startActivity(new Intent(getApplicationContext(), memprofile.class));
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

    private void getdoc() {

        @SuppressLint("StaticFieldLeak")
        class getdoctask extends AsyncTask<Void,Void,List<docterlist>> {

            String num = shp3.getString("hosid","");

            @Override
            protected List<docterlist> doInBackground(Void... voids) {

                return Mydatabase.getInstance(getApplicationContext()).doctordao().getall(num);
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(List<docterlist> docterlist) {
                super.onPostExecute(docterlist);

                doclis.clear();
                doclis.addAll(docterlist);
                docadapter.notifyDataSetChanged();

                docrecycler.smoothScrollToPosition(0);
                docrecycler.smoothScrollToPosition(0);


            }

        }

        new getdoctask().execute();
    }

    @Override
    public void onclick(docterlist docterlist, int position){
        Intent intent =  new Intent(getApplicationContext(), doclist.class);
        intent.putExtra("isview",true);
        intent.putExtra("user",docterlist);
        launcher.launch(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}