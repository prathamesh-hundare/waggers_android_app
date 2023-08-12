package com.example.adminpanel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        new Handler().postDelayed(() -> {

            onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);

            boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

            if(isFirstTime){

                SharedPreferences.Editor editor = onBoardingScreen.edit();
                editor.putBoolean("firstTime",false);
                editor.commit();

                startActivity(new Intent(getApplicationContext(),Onboarding.class));
                finish();
            }

            else{
                startActivity(new Intent(getApplicationContext(),LoginScreen.class));
                finish();
            }

        },1500);
    }
}