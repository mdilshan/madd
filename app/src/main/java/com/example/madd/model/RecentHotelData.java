package com.example.madd.model;

import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

public class RecentHotelData {

    String hotelName;
    String CityName;
    Integer imageUrl;
    String Document;



    public RecentHotelData(String id, String hotel_name, String location, int hotel2) {
        this.hotelName = hotelName;
        CityName = location;
        this.imageUrl = hotel2;
        Document = id;
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

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}