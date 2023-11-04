package com.example.preggycare.member;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.preggycare.R;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.adapters.serviceadapter;
import com.example.preggycare.listners.servicelistener;
import com.example.preggycare.modelclass.servicemodel;
import com.example.preggycare.serviceitem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class memservices extends AppCompatActivity implements servicelistener {

    RecyclerView serrecycler;

    FloatingActionButton serbtn;

    com.example.preggycare.adapters.serviceadapter serviceadapter;

    private ActivityResultLauncher<Intent> launcher;

    List<servicemodel> serlist;

    SharedPreferences shp3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memservices);

        shp3 = getSharedPreferences("mypreferences",MODE_PRIVATE);

        serrecycler =  findViewById(R.id.servicesrecycler);
        serbtn = findViewById(R.id.sbutton);
        serrecycler.setLayoutManager(new LinearLayoutManager(this));
        serlist = new ArrayList<>();
        serviceadapter = new serviceadapter(serlist,this);
        serrecycler.setAdapter(serviceadapter);

        serbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch(new Intent(getApplicationContext(), serviceitem.class));

            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        getservice();
                    }else{
                        Log.e(TAG,"Error :"+result.getResultCode());
                    }
                });
        getservice();

    }

    private void getservice() {

        @SuppressLint("StaticFieldLeak")
        class getsertask extends AsyncTask<Void,Void,List<servicemodel>>{
            String num = shp3.getString("memnumber","");

            @Override
            protected List<servicemodel> doInBackground(Void... voids) {
                return Mydatabase.getInstance(getApplicationContext()).servicedao().getall(num);
            }
            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(List<servicemodel> servicemodel) {

                super.onPostExecute(servicemodel);

                serlist.clear();
                serlist.addAll(servicemodel);
                serviceadapter.notifyDataSetChanged();

                serrecycler.smoothScrollToPosition(0);
                serrecycler.smoothScrollToPosition(0);

            }

        }
        new getsertask().execute();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onitemclick(servicemodel servicemodel, int position) {
        Intent intent =  new Intent(getApplicationContext(),serviceitem.class);
        intent.putExtra("isview",true);
        intent.putExtra("user",servicemodel);
        launcher.launch(intent);
    }
}