package com.example.madd.model;

import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

public class RecentHotelData {

    String hotelName;
    String CityName;
    String  imageUrl;
    String Document;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public RecentHotelData(String document, String hotelName, String location, String ImageUrl) {
        this.hotelName = hotelName;
        this.CityName = location;
        this.imageUrl = ImageUrl;
        this.Document = document;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }


    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    }