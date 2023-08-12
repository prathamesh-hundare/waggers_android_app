package com.example.adminpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpScreen extends AppCompatActivity {
    TextView lul;
    Button su;
    EditText e1,e2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        lul=(TextView) findViewById(R.id.SignUpLogInLink);
        su=(Button) findViewById(R.id.signUpBtn);
        e1=(EditText) findViewById(R.id.SignUpUserEmail);
        e2=(EditText) findViewById(R.id.SignUpUserPassword);
        mAuth=FirebaseAuth.getInstance();

        lul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpScreen.this,LoginScreen.class));
            }
        });

        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail,pass;
                mail= String.valueOf(e1.getText());
                pass= String.valueOf(e2.getText());
                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(SignUpScreen.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(SignUpScreen.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }

                mAuth.createUserWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpScreen.this, "SignUp Success",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignUpScreen.this,LoginScreen.class));
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUpScreen.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}