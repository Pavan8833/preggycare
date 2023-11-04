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

import com.example.preggycare.listners.medicinelistner;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.adapters.medadapter;
import com.example.preggycare.modelclass.usermed;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class medicines extends AppCompatActivity implements medicinelistner {

    RecyclerView medrecycler;

    FloatingActionButton medbtn;

    medadapter medadapter;

    private ActivityResultLauncher<Intent> launcher;

    List<usermed> medlist;

    SharedPreferences shp3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        shp3 = getSharedPreferences("mypreferences",MODE_PRIVATE);

        medbtn = findViewById(R.id.mflb);
        medrecycler  = findViewById(R.id.medrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        medrecycler.setLayoutManager(layoutManager);

        medlist = new ArrayList<>();
        medadapter = new medadapter(medlist,this);

        medrecycler.setAdapter(medadapter);

        medbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch(new Intent(getApplicationContext(),usermeditem.class));

            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                        if(result.getResultCode() == RESULT_OK){
                            getmed();
                        }else{
                            Log.e(TAG,"Error :"+result.getResultCode());
                        }
                });
        getmed();


    }

    private void getmed() {



        @SuppressLint("StaticFieldLeak")
        class getmedtask extends AsyncTask<Void,Void,List<usermed>> {

            String num = shp3.getString("usernumber","");

            @Override
            protected List<usermed> doInBackground(Void... voids) {

                return Mydatabase.getInstance(getApplicationContext()).usermeddao().getallmeds(num);
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(List<usermed> usermed) {
                super.onPostExecute(usermed);

                medlist.clear();
                medlist.addAll(usermed);
                medadapter.notifyDataSetChanged();

                medrecycler.smoothScrollToPosition(0);
                medrecycler.smoothScrollToPosition(0);


            }

        }

        new getmedtask().execute();
    }

    @Override
    public void onitemClicked(usermed usermed, int position){
        Intent intent =  new Intent(getApplicationContext(),usermeditem.class);
        intent.putExtra("isview",true);
        intent.putExtra("user",usermed);
        launcher.launch(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}