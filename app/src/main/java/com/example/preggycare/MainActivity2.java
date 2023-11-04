package com.example.preggycare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.preggycare.member.memsignup;

public class MainActivity2 extends AppCompatActivity {
    Button next,skip;
    TextView click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        next = (Button)findViewById(R.id.btn3);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =  new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(intent1);
            }
        });
        skip = (Button) findViewById(R.id.btn2);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(),MainActivity4.class);
                startActivity(intent2);
            }
        });

        click = findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), memsignup.class);
                startActivity(intent3);
            }
        });
    }
}