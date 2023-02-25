package com.example.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class addRemoveWalker extends AppCompatActivity {
    ListView lv;
    String walkers[]={"Walker 1","Walker 2","Walker 3","Walker 4","Walker 5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove_walker);

        lv=(ListView) findViewById(R.id.lv);

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,walkers);

        lv.setAdapter(ad);
    }
}