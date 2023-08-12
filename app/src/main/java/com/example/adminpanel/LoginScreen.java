package com.example.adminpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {
    TextView sul,al;
    EditText e1,e2;
    Button pi;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        sul=(TextView) findViewById(R.id.signUpLink);
        pi=(Button) findViewById(R.id.pawin);
        e1=(EditText) findViewById(R.id.username);
        e2=(EditText) findViewById(R.id.userpassword);
        al=(TextView) findViewById(R.id.adminLink);
        mAuth=FirebaseAuth.getInstance();

        al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this,MainActivity.class));
            }
        });

        sul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this,SignUpScreen.class));
            }
        });

        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String umail,upass;
                umail= String.valueOf(e1.getText());
                upass=String.valueOf(e2.getText());

                if(TextUtils.isEmpty(umail)){
                    Toast.makeText(LoginScreen.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(upass)){
                    Toast.makeText(LoginScreen.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }

                mAuth.signInWithEmailAndPassword(umail, upass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(LoginScreen.this, UserDashboard.class));
                                    Toast.makeText(LoginScreen.this, "Authentication success.",
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(LoginScreen.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}