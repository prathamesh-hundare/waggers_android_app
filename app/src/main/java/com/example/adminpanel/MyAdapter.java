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

public class MyAdapter extends ArrayAdapter<walkers> {
    ArrayList<walkers> wlist = new ArrayList<>();

    public MyAdapter(Context context, int tvR, ArrayList<walkers> objects) {
        super(context,tvR,objects);
        wlist = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }


    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(R.layout.list_item,null);
        TextView t1=(TextView) v.findViewById(R.id.name);
        TextView t2=(TextView) v.findViewById(R.id.age);
        TextView t3=(TextView) v.findViewById(R.id.phone);
        Button btn= (Button) v.findViewById(R.id.deleteWalker);

        t1.setText(wlist.get(pos).getName());
        t2.setText(wlist.get(pos).getAge());
        t3.setText(wlist.get(pos).getPhone());


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(t1.getContext());
                builder.setTitle("Are you sure ?");

                builder.setMessage("Once deleted, cannot be undone...");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String rn= (String) t1.getText();

                        DatabaseReference iw = FirebaseDatabase.getInstance().getReference().child("walkers");

                        iw.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ts : snapshot.getChildren()){
                                    if((ts.child("name").getValue().toString()).equals(rn)){
                                        String s = ts.getKey();

                                        DatabaseReference r =FirebaseDatabase.getInstance().getReference("walkers").child(s);
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

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
