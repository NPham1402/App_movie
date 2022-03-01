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

public class Login extends AppCompatActivity {
    TextInputEditText email,password;
    Button login;
    TextView signup;
    int RC_SIGN_IN=123;
    CardView Google,phoneLoginForm;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.tv_email);
        password=findViewById(R.id.tv_passowrd);
        Google=findViewById(R.id.cvGoogleLogin);
        phoneLoginForm=findViewById(R.id.cvPhoneLogin);
        login=findViewById(R.id.btn_login);
        createrequest();
        signup=findViewById(R.id.tv_signup);
        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            loadui(auth.getUid());
        }
        phoneLoginForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),PhoneLoginActivity.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),Signup.class);
                startActivity(intent);
            }
        });
        Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString()!=""&&password.getText().toString()!=""||password.getText().toString()!=""||email.getText().toString()!=""){
                    Toast.makeText(getBaseContext(), "Enter",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = auth.getCurrentUser();
                                Toast.makeText(getBaseContext(), "sucess.",
                                        Toast.LENGTH_SHORT).show();
                                loadui(auth.getUid());
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
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
    void loadui(String id){
        Intent intent=new Intent(getBaseContext(),Main.class);
        intent.putExtra("id",id);
        startActivity(intent);
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
                            loadui(auth.getUid());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("check", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
}