package com.example.madd.model;

public class RecentsData {

    String placeName;
    String cityName;
    String ImageUrl;
    String Document;

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String IImageUrl) {
        this.ImageUrl = IImageUrl;
    }

    public RecentsData(String document, String placeName, String cityName, String imageUrl) {
        this.placeName = placeName;
        this.cityName = cityName;
        this.ImageUrl = imageUrl;
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
