package com.example.preggycare;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.modelclass.memDao;
import com.example.preggycare.modelclass.userDao;
import com.example.preggycare.modelclass.usermodel;


public class MainActivity4 extends AppCompatActivity {

    private EditText signupnum,signupPassword;

    private CheckBox patientcheckbox;
    private Button signupButton;
    Mydatabase mydatabase;
    userDao userDao;
    Boolean isAllowed = false;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

           login = findViewById(R.id.login);
           signupnum = findViewById(R.id.signupnum);
           signupPassword = findViewById(R.id.password);
           signupButton = findViewById(R.id.signupButton);
           mydatabase = Mydatabase.getInstance(getApplicationContext());
           userDao = mydatabase.getDao();

//           String username = signupnum.getText().toString();
//           String password = signupPassword.getText().toString();
//

           signupnum.addTextChangedListener(new TextWatcher() {
                   @Override
                   public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                   }

                   @Override
                   public void onTextChanged(CharSequence s, int start, int before, int count) {

                   }

                   @Override
                   public void afterTextChanged(Editable s) {
                       String username =s.toString();
                       if(userDao.isPresent(username)){
                          isAllowed = false;
                           Toast.makeText(MainActivity4.this, "number is already registered", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           isAllowed = true;
                       }


                   }
               });
           signupButton.setOnClickListener(v -> {
               String username = signupnum.getText().toString();
               String password = signupPassword.getText().toString();

               if(isAllowed && !username.equals("") && !password.equals("")){
                   usermodel usermodel = new usermodel(0,username,password);
                   userDao.insert(usermodel);
                   Toast.makeText(MainActivity4.this, "Account is created", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(getApplicationContext(), MainActivity5.class);
                   startActivity(intent);
               }
               else{
                   Toast.makeText(MainActivity4.this, "fill the details", Toast.LENGTH_SHORT).show();
               }

           });

           login.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(getApplicationContext(), MainActivity5.class);
                   startActivity(intent);
               }
           });





        }
}

