package com.example.madd.model;

public class Hoteldto {
    public String id;
    public String user_id;
    public String hotel_name;
    public String hotel_location;
    public String hotel_description;
    public String hotel_img_url;
    public String hotel_price;



    public Hoteldto() {
    }

    public Hoteldto(String id, String user_id, String hotel_name, String hotel_location, String hotel_description, String hotel_img_url) {
        this.id = id;
        this.user_id = user_id;
        this.hotel_name = hotel_name;
        this.hotel_location = hotel_location;
        this.hotel_description = hotel_description;
        this.hotel_img_url = hotel_img_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_location() {
        return hotel_location;
    }

    public void setHotel_location(String hotel_location) {
        this.hotel_location = hotel_location;
    }

    public String getHotel_description() {
        return hotel_description;
    }

    public void setHotel_description(String hotel_description) {
        this.hotel_description = hotel_description;
    }

    public String getHotel_img_url() {
        return hotel_img_url;
    }

    public void setHotel_img_url(String hotel_img_url) {
        this.hotel_img_url = hotel_img_url;
    }

    public String getHotel_price() {
        return hotel_price;
    }

    public void setHotel_price(String hotel_price) {
        this.hotel_price = hotel_price;
    }
}
