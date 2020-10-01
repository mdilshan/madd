package com.example.madd;

/**
 * Model class used by ReviewListAdapter
 * @Author - https://github.com/mdilshan
 */
public class ReviewInterface {
    public String id;
    public String name;
    public String posted_by;
    public String comment;
    public double rating;
    public ReviewInterface(String id, String name, String posted_by, String comment, double rating) {
        this.id = id;
        this.name = name;
        this.posted_by = posted_by;
        this.comment = comment;
        this.rating = rating;
    }
}
