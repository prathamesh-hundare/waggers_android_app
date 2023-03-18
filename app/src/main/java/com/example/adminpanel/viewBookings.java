package com.example.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class viewBookings extends AppCompatActivity {
    ListView lv11;
    String bookings[]={"Pratham booked a walker","Vedang booked a walker","Yash booked a walker"};
    ArrayAdapter<String> ad;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

        lv11=(ListView) findViewById(R.id.lv11);

        ad = new ArrayAdapter<String>(this,R.layout.bookings,R.id.appointment,bookings);
        lv11.setAdapter(ad);

    }
}