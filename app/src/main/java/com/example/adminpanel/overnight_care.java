package com.example.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class overnight_care extends AppCompatActivity {

    ImageView oc_image;
    ImageView backBtn;
    TextView oc_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overnight_care);

        backBtn = findViewById(R.id.back_pressed);
        oc_image = findViewById(R.id.oc_image);
        oc_text = findViewById(R.id.oc_text);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overnight_care.super.onBackPressed();
            }
        });
    }
}