package com.example.madd.model;

public class RecentsData {

    String placeName;
    String cityName;
    int imageUrl;
    String Document;

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int  imageUrl) {
        this.imageUrl = imageUrl;
    }

    public RecentsData(String document,String placeName, String cityName,  int imageUrl) {
        this.placeName = placeName;
        this.cityName = cityName;
        this.imageUrl = imageUrl;
        this.Document = document;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCityName() { return cityName; }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


}
