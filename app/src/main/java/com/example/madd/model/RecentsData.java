package com.example.madd.model;

public class RecentsData {

    String placeName;
    String cityName;
    String distance;
    Integer imageUrl;

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public RecentsData(String placeName, String cityName, String distance, Integer imageUrl) {
        this.placeName = placeName;
        this.cityName = cityName;
        this.distance = distance;
        this.imageUrl = imageUrl;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
