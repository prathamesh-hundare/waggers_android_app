package com.example.adminpanel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class book_walker extends AppCompatActivity {

    EditText bwname, bwnumber;
    Button bwdate, bwtime, bookbtn;
    TextView wc;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_walker);

        bwname = findViewById(R.id.bwname);
        bwnumber = findViewById(R.id.bwnumber);
        bwdate = findViewById(R.id.bwdate);
        bwtime = findViewById(R.id.bwtime);
        bookbtn = findViewById(R.id.bookbtn);
        wc=(TextView) findViewById(R.id.walkerCount);




        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("walkers");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String co = String.valueOf(snapshot.getChildrenCount());
                wc.setText(co);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backBtn = findViewById(R.id.back_pressed);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book_walker.super.onBackPressed();
            }
        });

        bwdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });

        bwtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime();
            }
        });

        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ename = String.valueOf(bwname.getText());
                String enumb = String.valueOf(bwnumber.getText());
                String edate = (String) bwdate.getText();
                String etime = (String) bwtime.getText();

                //public void addData(String e1,String e2,String e3){
                  //  String name=e1;
                    //String age=e2;
                    //String no=e3;

                    DatabaseReference iw = FirebaseDatabase.getInstance().getReference().child("bookings");

                    insertBookings bw = new insertBookings(ename,enumb,edate,etime);

                    iw.push().setValue(bw);
                    Toast.makeText(book_walker.this, "Booked Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(book_walker.this, UserDashboard.class));
            }
        });

    }

    private void setDate()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                String showDate = date+" / "+(month+1)+" / "+year;
                bwdate.setText(showDate);
            }
        },year,month,date);
        datePickerDialog.show();
    }

    private void setTime()
    {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                int nh;
                String showTime;
                if(hour>12){
                    nh=hour-12;
                    showTime=nh+":"+min+" pm";

                }
                else{
                    showTime=hour+":"+min+" am";
                }


                bwtime.setText(showTime);
            }
        },hour, min, false);
        timePickerDialog.show();
    }
}