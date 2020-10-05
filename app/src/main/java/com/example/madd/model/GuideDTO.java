package com.example.madd.model;

public class GuideDTO {
    String GuideName;
    String Place;
    String Rating;
    String ImageUrl;
    Float RatingBar;
    String posted_on;

    public void setRatingBar(Float ratingBar) {
        RatingBar = ratingBar;
    }

    public String getPosted_on() {
        return posted_on;
    }

    public void setPosted_on(String posted_on) {
        this.posted_on = posted_on;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    String about;
    String Contact;
    public Float getRatingBar() {
        this.RatingBar = Float.parseFloat(Rating);
        return RatingBar;
    }

    public String getGuideName() {
        return GuideName;
    }

    public void setGuideName(String guideName) {
        GuideName = guideName;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public GuideDTO(String GuideName, String posted_on, String about,String Rating,String Place,String Contact , String ImageUrl) {
        this.GuideName = GuideName;
        this.Place = Place;
        this.Rating = Rating;
        this.ImageUrl =ImageUrl;
        this.about = about;
        this.posted_on =posted_on;
        this.Contact = Contact;
    }
}
