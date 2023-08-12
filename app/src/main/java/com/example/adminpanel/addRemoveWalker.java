package com.example.adminpanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class addRemoveWalker extends AppCompatActivity {
    ListView lv;
    Button aw;
    ArrayList<walkers> wlist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove_walker);

        lv=(ListView) findViewById(R.id.lv);
        MyAdapter adapter = new MyAdapter(this,R.layout.list_item,wlist);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("walkers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                wlist.clear();
                for(DataSnapshot st : snapshot.getChildren()){
                    String na = st.child("name").getValue().toString();
                    String ag = st.child("age").getValue().toString();
                    String ph = st.child("no").getValue().toString();
                    wlist.add(new walkers(na,ag,ph));
                }

                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lv.setAdapter(adapter);







        aw=(Button) findViewById(R.id.addWalker);

        aw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(addRemoveWalker.this);
                builder.setTitle("Adding a Walker");
                final View custom_layout = getLayoutInflater().inflate(R.layout.add_walker_dialog,null);
                builder.setView(custom_layout);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText e1 = custom_layout.findViewById(R.id.ename);
                        EditText e2 = custom_layout.findViewById(R.id.eage);
                        EditText e3 = custom_layout.findViewById(R.id.ephone);

                        addData(e1.getText().toString(),e2.getText().toString(),e3.getText().toString());

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            public void addData(String e1,String e2,String e3){
                String name=e1;
                String age=e2;
                String no=e3;

                DatabaseReference iw = FirebaseDatabase.getInstance().getReference().child("walkers");

                insertWalkers walker = new insertWalkers(name,age,no);

                iw.push().setValue(walker);
                Toast.makeText(addRemoveWalker.this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });


    }
}