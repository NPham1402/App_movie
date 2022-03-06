package com.example.app_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    TextInputEditText email,password,confirmpassword;
    Button signup;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.tv_email_signup);
        password=findViewById(R.id.tv_passowrd_signup);
        auth=FirebaseAuth.getInstance();
        confirmpassword=findViewById(R.id.tv_passowrd_confirm_signup);
        signup=findViewById(R.id.btn_signup);
        confirmpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (password.getText().toString()==confirmpassword.getText().toString()){

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (password.getText().toString()==""){
                    confirmpassword.setText("");
                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(SignUpActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getBaseContext(),"sucess",Toast.LENGTH_SHORT);
                            Intent intent=new Intent(getBaseContext(), UserLoginActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getBaseContext(),"false",Toast.LENGTH_SHORT);

                        }
                    }
                });
            }
        });
    }
}