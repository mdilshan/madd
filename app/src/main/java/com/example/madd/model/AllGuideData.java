package com.example.madd.model;

public class AllGuideData {

    String GuideName;
    String Place;
    String Price;
    String Rating;
    Integer ImageUrl;

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }

    String Document;

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

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public Integer getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        ImageUrl = imageUrl;
    }

    public AllGuideData(String document,String GuideName, String Place, String Price, String Rating, Integer ImageUrl) {
        this.GuideName = GuideName;
        this.Place = Place;
        this.Price = Price;
        this.ImageUrl =ImageUrl;
        this.Document = document;
    }

}
