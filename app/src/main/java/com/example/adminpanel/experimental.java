package com.example.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class experimental extends AppCompatActivity {

    FirebaseUser user;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experimental);
        TextView t=(TextView) findViewById(R.id.expo);
        auth=FirebaseAuth.getInstance();
        user= auth.getCurrentUser();
        if(user==null){
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            t.setText(user.getEmail());
        }
    }
}