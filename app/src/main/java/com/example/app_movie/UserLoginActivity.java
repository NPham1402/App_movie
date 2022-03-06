package com.example.app_movie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class UserLoginActivity extends AppCompatActivity {
    TextInputEditText ipEmail, ipPassword;
    Button btnLogin;
    TextView tvSignUp;
    int RC_SIGN_IN=123;
    CardView cvGoogle_UserLoginActivity, cvPhone_UserLoginActivity;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            loadUi(auth.getUid());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ipEmail =findViewById(R.id.tv_email);
        ipPassword =findViewById(R.id.tv_passowrd);
        cvGoogle_UserLoginActivity =findViewById(R.id.cvGoogleLogin);
        cvPhone_UserLoginActivity =findViewById(R.id.cvPhoneLogin);
        btnLogin =findViewById(R.id.btn_login);
        createrequest();
        tvSignUp =findViewById(R.id.tvSignUp);

        cvPhone_UserLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLoginActivity.this,PhoneLoginActivity.class));
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        cvGoogle_UserLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ipEmail.getText().toString()!=""&& ipPassword.getText().toString()!=""|| ipPassword.getText().toString()!=""|| ipEmail.getText().toString()!=""){
                    Toast.makeText(getBaseContext(), "Enter",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    auth.signInWithEmailAndPassword(ipEmail.getText().toString(), ipPassword.getText().toString()).addOnCompleteListener(UserLoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = auth.getCurrentUser();
                                Toast.makeText(getBaseContext(), "sucess.",
                                        Toast.LENGTH_SHORT).show();
                                loadUi(auth.getUid());
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getBaseContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    };
    private void  createrequest(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("573880327975-ago46m5ln4s95elvb2524c4n7i1v8t2s.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("check", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("check", "Google sign in failed", e);
            }
        }
    }
    void loadUi(String id){
        Intent intent=new Intent(getBaseContext(),Main.class);
        startActivity(intent);
        finish();
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("checl", "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            loadUi(auth.getUid());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("check", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
}