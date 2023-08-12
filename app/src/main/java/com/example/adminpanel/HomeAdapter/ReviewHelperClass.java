package com.example.adminpanel.HomeAdapter;

public class ReviewHelperClass {
    int image;
    String username, review;

    public ReviewHelperClass(int image, String username, String review) {
        this.image = image;
        this.username = username;
        this.review = review;
    }

    public int getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public String getReview() {
        return review;
    }
}
