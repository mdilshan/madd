package com.example.madd.model;

public class GuideRecentsData {

    String GuideName;
    String Place;
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

    public Integer getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        ImageUrl = imageUrl;
    }

    public GuideRecentsData(String document,String GuideName, String Place, Integer ImageUrl) {
        this.GuideName = GuideName;
        this.Place = Place;
        this.ImageUrl =ImageUrl;
        this.Document = document;
    }

}
