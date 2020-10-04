package com.example.madd.model;

/**
 * Model for Recents Guide
 * @Author - https://github.com/MhmdAsq
 */
public class GuideRecentsData {

    String GuideName;
    String Place;
    String ImageUrl;
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

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public GuideRecentsData(String document,String GuideName, String Place, String ImageUrl) {
        this.GuideName = GuideName;
        this.Place = Place;
        this.ImageUrl =ImageUrl;
        this.Document = document;
    }

}
