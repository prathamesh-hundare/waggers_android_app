package com.example.adminpanel;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button btn;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //Sign In Directly
            startActivity(new Intent(MainActivity.this,afterlogin.class));
        }
        else{

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.username);
        e2=(EditText) findViewById(R.id.userpassword);
        btn=(Button) findViewById(R.id.login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=e1.getText().toString();
                String password=e2.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    updateUI(user);
                                    startActivity(new Intent(MainActivity.this,afterlogin.class));
                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

//                if(TextUtils.equals(name,"admin") && TextUtils.equals(pass,"password") ){
//                    Toast.makeText(MainActivity.this, "Login Successful..", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this,afterlogin.class));
//                }
//                else if (name!="admin" || pass!="admin"){
//                    Toast.makeText(MainActivity.this,"Wrong Credentials",Toast.LENGTH_SHORT).show();
//                }
//                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pass)){
//                    Toast.makeText(MainActivity.this, "Enter Details", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}