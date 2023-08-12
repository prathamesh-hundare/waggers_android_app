package com.example.adminpanel.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminpanel.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    ArrayList<ContactHelperClass> contacts;

    public ContactAdapter(ArrayList<ContactHelperClass> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card_design,parent,false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactHelperClass contactHelperClass = contacts.get(position);

        holder.image.setImageResource(contactHelperClass.getImage());
        holder.id.setText(contactHelperClass.getId());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView id;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.clogos);
            id = itemView.findViewById(R.id.ids);
        }
    }

}
