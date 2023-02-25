package com.example.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.username);
        e2=(EditText) findViewById(R.id.userpassword);
        btn=(Button) findViewById(R.id.login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e1.getText().toString();
                String pass=e2.getText().toString();

                if(TextUtils.equals(name,"admin") && TextUtils.equals(pass,"password") ){
                    Toast.makeText(MainActivity.this, "Login Successful..", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,afterlogin.class));
                }
                else if (name!="admin" || pass!="admin"){
                    Toast.makeText(MainActivity.this,"Wrong Credentials",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pass)){
                    Toast.makeText(MainActivity.this, "Enter Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}