package com.example.madd;

public class ReviewInterface {
    public String name;
    public String posted_by;
    public String comment;
    public double rating;
    ReviewInterface(String name, String posted_by, String comment, double rating) {
        this.name = name;
        this.posted_by = posted_by;
        this.comment = comment;
        this.rating = rating;
    }
}
