package com.example.madd.model;

public class AllPlaceData {
    String PlaceName;
    String PlaceLocation;
    //String Distance;
    String Rating;
    String ImageUrl;
    String Document;

    public AllPlaceData(String id, String placeName, String placeLocation,  String rating, String imageUrl) {
        PlaceName = placeName;
        PlaceLocation = placeLocation;
        //Distance = distance;
        Rating = rating;
        ImageUrl = imageUrl;
        Document = id;

    }

    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public String getPlaceLocation() {
        return PlaceLocation;
    }

    public void setPlaceLocation(String placeLocation) {
        PlaceLocation = placeLocation;
    }

    //public String getDistance() {
     //   return Distance;
   // }

   // public void setDistance(String distance) {
        //Distance = distance;
    //}

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

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }
}
