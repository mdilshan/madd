package com.example.madd.model;

public class AllHotelsData {

    String hotelName;
    String location;
    String about;
    String price;
    String imageUrl;
    String Rating;
    Float RatingBar;
    String Document;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public Float getRatingBar() {
        return RatingBar;
    }

    public void setRatingBar(Float ratingBar)
    {this.RatingBar = Float.parseFloat(Rating);
        RatingBar = ratingBar;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }

    public AllHotelsData(String document,String hotelName, String location,String rating, String imageUrl) {
        this.hotelName = hotelName;
        this.location = location;
        this.imageUrl = imageUrl;
        Rating = rating;
        Document = document;
        this.RatingBar = Float.parseFloat(Rating);

    }
}