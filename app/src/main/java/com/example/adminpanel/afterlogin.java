package com.example.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class afterlogin extends AppCompatActivity {
    Button b1;
    ImageView iv1,iv2;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);
        b1 = (Button) findViewById(R.id.logout);
        iv1=(ImageView) findViewById(R.id.imageView);
        iv2=(ImageView) findViewById(R.id.imageView3);
        t1=(TextView) findViewById(R.id.textView3);
        t2=(TextView) findViewById(R.id.viewBookings);

        b1.setOnClickListener(this::onClick);
        iv1.setOnClickListener(this::onClick);
        iv2.setOnClickListener(this::onClick);
        t1.setOnClickListener(this::onClick);
        t2.setOnClickListener(this::onClick);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.logout:
                finish();
                break;
            case R.id.imageView:
                startActivity(new Intent(afterlogin.this,addRemoveWalker.class));
                break;
            case R.id.imageView3:
                startActivity(new Intent(afterlogin.this,viewBookings.class));
                break;
            case R.id.textView3:
                startActivity(new Intent(afterlogin.this,addRemoveWalker.class));
                break;
            case R.id.viewBookings:
                startActivity(new Intent(afterlogin.this,viewBookings.class));
                break;
        }
    }
}