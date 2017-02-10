package com.example.devesh.place_my_meal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText userName,passWord;
    TextView signUp;
    Button submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.userID);
        passWord = (EditText) findViewById(R.id.password);
        signUp = (TextView) findViewById(R.id.SignUp);
        submit = (Button) findViewById(R.id.Login);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = userName.getText().toString().trim();
                String pass = passWord.getText().toString().trim();



            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
