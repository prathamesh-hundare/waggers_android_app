package com.example.adminpanel;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterBookings extends ArrayAdapter<bookings> {
    ArrayList<bookings> blist = new ArrayList<>();

    public AdapterBookings(Context context, int tvR, ArrayList<bookings> objects) {
        super(context,tvR,objects);
        blist = objects;
    }


    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(R.layout.bookings,null);
        TextView t1=(TextView) v.findViewById(R.id.bookingName);
        TextView t2=(TextView) v.findViewById(R.id.bookingnum);
        TextView t3=(TextView) v.findViewById(R.id.bookingTime);
        TextView t4=(TextView) v.findViewById(R.id.bookingdate);
        Button bt = (Button) v.findViewById(R.id.cancelBooking);

        t1.setText(blist.get(pos).getName());
        t2.setText(blist.get(pos).getTime());
        t3.setText(blist.get(pos).getNum());
        t4.setText(blist.get(pos).getDate());


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(t1.getContext());
                builder.setTitle("Cancel Booking?");

                builder.setMessage("After cancellation, user will have to book again through the app..");
                builder.setPositiveButton("Yes, Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String rn= (String) t1.getText();

                        DatabaseReference iw = FirebaseDatabase.getInstance().getReference().child("bookings");

                        iw.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ts : snapshot.getChildren()){
                                    if((ts.child("name").getValue().toString()).equals(rn)){
                                        String s = ts.getKey();

                                        DatabaseReference r =FirebaseDatabase.getInstance().getReference("bookings").child(s);
                                        r.removeValue();


                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(t1.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });


        return v;
    }
}
