package com.example.madd.model;

public class PlaceDto {
    String id;
    String user_id;
    String name;
    String location;
    String dscription;
    String img_url;

    public PlaceDto(String id, String user_id, String name, String location, String dscription, String img_url) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.location = location;
        this.dscription = dscription;
        this.img_url = img_url;
    }
}
