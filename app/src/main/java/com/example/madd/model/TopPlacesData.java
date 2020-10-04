package com.example.madd.model;

public class TopPlacesData {

    String placeName;
    String cityName;
    String distance;
    Integer imageUrl;
    String Rating;
    Float PlaceRateBar;

    public Float getPlaceRateBar() {
        this.PlaceRateBar = Float.parseFloat(Rating);
        return PlaceRateBar;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public TopPlacesData(String placeName, String cityName, String distance,String Rating, Integer imageUrl) {
        this.placeName = placeName;
        this.cityName = cityName;
        this.distance = distance;
        this.imageUrl = imageUrl;
        this.Rating = Rating;
        this.PlaceRateBar = Float.parseFloat(Rating);
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }
}
