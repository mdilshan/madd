package com.example.madd.model;

public class AllHotelsData {

    String hotelName;
    String location;
    String about;
    String price;
    Integer imageUrl;
    String Rating;
    String Document;


    public AllHotelsData(String id, String hotel_name, String about, String location, String rating, int hotel2) {
        this.hotelName = hotel_name;
        this.location = location;
        //this.price = Price;
        this.about = about;
        this.imageUrl =hotel2;
        this.Document = id;

    }

//    public AllHotelsData(String id, String hotel_name, String place, String location, String rating, int hotel2) {
//    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
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
        return location;
    }

    public void setCityName(String cityName) {
        location = cityName;
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