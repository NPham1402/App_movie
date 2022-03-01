package com.example.app_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneLoginActivity extends AppCompatActivity {
    EditText ipPhoneNo,ipOTP;
    Button btnGetCode,btnSendCode;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    String verificationId;
    CardView cvUserLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        ipPhoneNo=findViewById(R.id.ipPhoneNo);
        ipOTP=findViewById(R.id.ipOTP);
        btnGetCode=findViewById(R.id.btnGetCode);
        btnSendCode=findViewById(R.id.btnSendCode);
        cvUserLogin=findViewById(R.id.cvUserLogin);
        cvUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhoneLoginActivity.this,Login.class));
            }
        });
        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipPhoneNoClone=ipPhoneNo.getText().toString();
                if(!ipPhoneNoClone.isEmpty()){
                    sendVerification(ipPhoneNoClone);
                }
                else{
                    Toast.makeText(PhoneLoginActivity.this, "Please fill with Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(ipOTP.getText().toString())){
                    verifyCode(ipOTP.getText().toString());
                }
                else
                    Toast.makeText(PhoneLoginActivity.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendVerification(String ipPhoneNoClone){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+84"+ipPhoneNoClone)       // Phone number to verify
                        .setTimeout(90L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(PhoneLoginActivity.this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            final String code=phoneAuthCredential.getSmsCode();
            if(code!=null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(PhoneLoginActivity.this, "Can't get OTP", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken token){
            super.onCodeSent(s,token);
            verificationId=s;

        }
    };
    private void verifyCode(String code){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,code);
        signInByCredential(credential);
    }
    private void signInByCredential(PhoneAuthCredential credential){
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(PhoneLoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PhoneLoginActivity.this,MainActivity.class));
                }
            }
        });
    }
}