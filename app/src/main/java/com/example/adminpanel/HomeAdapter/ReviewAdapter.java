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

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    ArrayList<ReviewHelperClass> reviews;

    public ReviewAdapter(ArrayList<ReviewHelperClass> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_card_design,parent,false);
        ReviewViewHolder reviewViewHolder = new ReviewViewHolder(view);
        return reviewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        ReviewHelperClass reviewHelperClass = reviews.get(position);

        holder.image.setImageResource(reviewHelperClass.getImage());
        holder.username.setText(reviewHelperClass.getUsername());
        holder.review.setText(reviewHelperClass.getReview());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView username, review;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);


            image = itemView.findViewById(R.id.profile);
            username = itemView.findViewById(R.id.username);
            review = itemView.findViewById(R.id.review);


        }
    }
}
