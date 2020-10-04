package com.example.madd.model;

public class TopPlacesData {

    String placeName;
    String cityName;
    //String distance;
    String ImageUrl;
    String Rating;
    Float PlaceRateBar;
    String Document;

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }


    public Float getPlaceRateBar() {
        this.PlaceRateBar = Float.parseFloat(Rating);
        return PlaceRateBar;
    }


    public TopPlacesData(String placeName, String cityName, String Rating, String imageUrl,String document ) {
        this.placeName = placeName;
        this.cityName = cityName;
        //this.distance = distance;
        this.ImageUrl = imageUrl;
        this.Rating = Rating;
        this.PlaceRateBar = Float.parseFloat(Rating);
        this.Document=document;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
}

