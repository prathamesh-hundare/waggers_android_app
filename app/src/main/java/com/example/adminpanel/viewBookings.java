package com.example.adminpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewBookings extends AppCompatActivity {
    ListView lv11;
    ArrayList<bookings> blist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

        lv11=(ListView) findViewById(R.id.lv11);
        AdapterBookings ad = new AdapterBookings(this,R.layout.bookings,blist);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("bookings");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                blist.clear();
                for(DataSnapshot st : snapshot.getChildren()){
                    String na = st.child("name").getValue().toString();
                    String tm = st.child("time").getValue().toString();
                    String num = st.child("num").getValue().toString();
                    String date = st.child("date").getValue().toString();
                    blist.add(new bookings(na,num,tm,date));
                }

                ad.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        lv11.setAdapter(ad);

    }
}