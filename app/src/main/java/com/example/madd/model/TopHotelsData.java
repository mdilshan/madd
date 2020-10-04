package com.example.madd.model;

public class TopHotelsData {

    String hotelName;
    String CityName;
    String price;
    String ImageUrl;
    String Rating;
    Float RatingBar;
    String Document;



    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.ImageUrl = imageUrl;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public Float getRatingBar() {
        this.RatingBar = Float.parseFloat(Rating);
        return RatingBar;
    }

    public void setRatingBar(Float ratingBar) {
        RatingBar = ratingBar;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        price = price;
    }

    public TopHotelsData(String document,String hotelName, String cityName,String rating, String imageUrl ) {
        this.hotelName = hotelName;
        CityName = cityName;
        this.ImageUrl = imageUrl;
        Rating = rating;
        Document = document;
        this.RatingBar = Float.parseFloat(Rating);
    }

}