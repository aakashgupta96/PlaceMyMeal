package com.example.devesh.place_my_meal;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private SignInButton mSignInButton;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mFirebaseAuth;
    Button bt1,bt2;
    EditText et1, et2;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference databaseReference;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mFirebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://placemymeal.firebaseio.com/");

        bt2 = (Button) findViewById(R.id.bt2);
        bt1 = (Button) findViewById(R.id.bt1);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        progressDialog = new ProgressDialog(this);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn() {
        String email = et1.getText().toString().trim();
        String password = et2.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(SignIn.this, "Username Empty", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(SignIn.this, "Password Empty", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(SignIn.this, "Password should be more than 6 characters", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setMessage("Working....");
            progressDialog.show();

            mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(SignIn.this,
                    new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        finish();
                        progressDialog.cancel();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        Toast.makeText(SignIn.this, "Email or password is incorrect", Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                }
            });
        }


    }
    
    private void RegisterUser(){
        String email = et1.getText().toString().trim();
        String password = et2.getText().toString().trim();

        if(email.isEmpty()){
            Toast.makeText(SignIn.this,"Username Empty",Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty()){
            Toast.makeText(SignIn.this,"Password Empty",Toast.LENGTH_SHORT).show();
        }
        else if(password.length()<6){
            Toast.makeText(SignIn.this,"Password should be more than 6 characters",Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.setMessage("Working....");
            progressDialog.show();

            mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(SignIn.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        databaseReference.child("consumers").push().setValue(mFirebaseAuth.getCurrentUser().getEmail());

                        finish();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                    else
                        Toast.makeText(SignIn.this,"Couldn't Register",Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();

                }
            });
        }

    }
    
}