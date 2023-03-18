package com.example.adminpanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        t1.setText(wlist.get(pos).getName());
        t2.setText(wlist.get(pos).getAge());
        t3.setText(wlist.get(pos).getPhone());
        return v;
    }
}

