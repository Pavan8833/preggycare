package com.example.preggycare;

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


import com.example.preggycare.listners.testlistner;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.adapters.testadapter;
import com.example.preggycare.modelclass.usertest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class tests extends AppCompatActivity implements testlistner {


    RecyclerView testrecycler;

    FloatingActionButton testbtn;

    testadapter testadapter;

    private ActivityResultLauncher<Intent> launcher;

    List<usertest> testlist;

    SharedPreferences shp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        shp3 = getSharedPreferences("mypreferences",MODE_PRIVATE);

        testbtn = findViewById(R.id.tflb);
        testrecycler = findViewById(R.id.testrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        testrecycler.setLayoutManager(layoutManager);

        testlist = new ArrayList<>();
        testadapter = new testadapter(testlist,this);
        testrecycler.setAdapter(testadapter);

        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch(new Intent(getApplicationContext(),usertestitem.class));
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        gettest();
                    }else{
                        Log.e(TAG,"Error :"+result.getResultCode());
                    }
                });
                gettest();

    }

    private void gettest() {

        class gettesttask extends AsyncTask<Void,Void,List<usertest>>{
            String num = shp3.getString("usernumber","");

            @Override
            protected List<usertest> doInBackground(Void... voids) {

                return Mydatabase.getInstance(getApplicationContext()).usertestdao().getalltests(num);
            }
            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(List<usertest> usertest) {
                super.onPostExecute(usertest);

                testlist.clear();
                testlist.addAll(usertest);
                testadapter.notifyDataSetChanged();

                testrecycler.smoothScrollToPosition(0);
                testrecycler.smoothScrollToPosition(0);


            }
        }

        new gettesttask().execute();

    }

    @Override
    public void onitemclicked(usertest usertest, int position) {
        Intent intent = new Intent(getApplicationContext(), usertestitem.class);
        intent.putExtra("isview", true);
        intent.putExtra("user", usertest);
        launcher.launch(intent);
    }

}