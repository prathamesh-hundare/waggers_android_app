package com.example.adminpanel.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminpanel.R;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {

    ArrayList<OfferHelperClass> offers;

    public OfferAdapter(ArrayList<OfferHelperClass> offers){
        this.offers = offers;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_card_design,parent,false);
        OfferViewHolder offerViewHolder = new OfferViewHolder(view);
        return offerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        OfferHelperClass offerHelperClass = offers.get(position);

        holder.image.setImageResource(offerHelperClass.getImage());
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public static class OfferViewHolder extends RecyclerView.ViewHolder{
        ImageView image;


        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.offer_card);
        }
    }
}
