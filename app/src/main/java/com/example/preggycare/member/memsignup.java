package com.example.preggycare.member;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preggycare.R;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.modelclass.memDao;
import com.example.preggycare.modelclass.memmodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class memsignup extends AppCompatActivity {
    EditText memsignupnum,Otp,mememail,memsignpassword;
    Button gotp,votp,memsignupbtn;
    Button memlogin;
    Button emaildialogbtn;
    FirebaseAuth mauth;
    Boolean isverified = false;
    Boolean isAllowed = false;
    String VerificationId;

    Mydatabase mydatabase;
    memDao memDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memsignup);
        memlogin = findViewById(R.id.memlogin);
        memsignupnum = findViewById(R.id.memsignupnum);
        gotp = findViewById(R.id.gotp);
        votp = findViewById(R.id.votp);
        Otp = findViewById(R.id.otp);
        memsignupbtn = findViewById(R.id.memsignupbtn);
        mauth = FirebaseAuth.getInstance();
        memsignpassword=findViewById(R.id.memsignpassword);

        mydatabase = Mydatabase.getInstance(getApplicationContext());
        memDao = mydatabase.memDao();

        String username = memsignupnum.getText().toString();
        String password = memsignpassword.getText().toString();

        memsignupnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String username = s.toString();
                if(memDao.isPresent(username)){
                    isAllowed= false;
                    Toast.makeText(memsignup.this, "Number is already registered", Toast.LENGTH_SHORT).show();
                }
                else{
                    isAllowed =true;
                }

            }
        });

        memlogin.setOnClickListener(v -> startActivity(new Intent(memsignup.this, memlogin.class)));



        gotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = memsignupnum.getText().toString();
                if(!number.equals("")){
                    sendverificationcode(number);

                }
            }
        });

        votp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = Otp.getText().toString();
                if(!otp.equals("")){
                  verifycode(otp);
                }
                else{
                    Toast.makeText(memsignup.this, "enter the correct otp", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //            public void sendEmail(){
//                String Email = "pavankumaravn141@gmail.com";
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_EMAIL,new String[] {Email});
//                intent.setType("message/rfc822");
//                startActivity(intent.createChooser(intent,"choose your email client :"));
//            }
        memsignupbtn.setOnClickListener( v -> {

            String name = memsignupnum.getText().toString();
            String pass = memsignpassword.getText().toString();


            if (isverified && !name.isEmpty() && !pass.isEmpty()) {
                memmodel memmodel = new memmodel(0, name, pass);
                memDao.insert(memmodel);
                Log.d("username", name);

                if (memDao.login(name, pass)) {
                    startActivity(new Intent(memsignup.this, memlogin.class));
                } else {
                    Toast.makeText(memsignup.this, "Account is not created", Toast.LENGTH_SHORT).show();
                }
            }
//
//                    Dialog dialog = new Dialog(memsignup.this);
//                    dialog.setContentView(R.layout.emaildialog);
//                    dialog.show();
//                   // for showcase purpose l
//
//                    emaildialogbtn = dialog.findViewById(R.id.emaildialogbtn);
//                    mememail = dialog.findViewById(R.id.mememail);
//                    emaildialogbtn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            String email = mememail.getText().toString();
//                            if (!email.equals("")) {
//                                memmodel memmodel = new memmodel(0, username, password);
//                                memDao.insert(memmodel);
//                                sendEmail();
//                                dialog.dismiss();
//                            }
//                        }
//                    });}7396893315
            else{
                Toast.makeText(memsignup.this, "verify your number", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void verifycode( String  code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId,code);
        signInbyPhoneAuthCredential(credential);
    }

    private void signInbyPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Toast.makeText(memsignup.this, "verification is successful", Toast.LENGTH_SHORT).show();
                        Dialog dialog = new Dialog(memsignup.this);
                        dialog.setContentView(R.layout.successdialog);
                        isverified = true;
                        dialog.show();;
                    }
                });

    }

    private void sendverificationcode(String phoneNumber) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mauth)
                        .setPhoneNumber("+91" + phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

                final String code = credential.getSmsCode();
                if(code != null){
                    verifycode(code);
                }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {


            Toast.makeText(memsignup.this, "Verification failed", Toast.LENGTH_SHORT).show();

            // Show a message and update the UI
        }

        @Override
        public void onCodeSent(@NonNull String s,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            //            Log.d(TAG, "onCodeSent:" + verificationId);

            // Save verification ID and resending token so we can use them later
            super.onCodeSent(s,token);
            VerificationId = s;
            Toast.makeText(memsignup.this, "code sent", Toast.LENGTH_SHORT).show();
            votp.setEnabled(true);
             //            mResendToken = token;
        }
    };

}