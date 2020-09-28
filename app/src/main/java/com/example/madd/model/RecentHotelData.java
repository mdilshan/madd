package com.example.madd.model;

import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

public class RecentHotelData {

    String hotelName;
    String CityName;
    String price;
    Integer imageUrl;





    public RecentHotelData(String hotelName, String CityName, String Price, Integer imageUrl) {
        this.hotelName = hotelName;
        this.CityName = CityName;
        this.price = Price;
        this.imageUrl =imageUrl;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        price = price;
    }
    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}