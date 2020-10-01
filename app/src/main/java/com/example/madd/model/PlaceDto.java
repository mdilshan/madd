package com.example.madd.model;

public class PlaceDto {

    public String id;
    public String user_id;
    public String name;
    public String location;
    public String description;
    public String img_url;

    public PlaceDto(String id, String user_id , String name, String location, String description, String img_url) {

        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.img_url = img_url;
    }


}
