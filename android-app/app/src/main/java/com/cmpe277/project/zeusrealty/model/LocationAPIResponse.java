package com.cmpe277.project.zeusrealty.model;

import java.util.List;

public class LocationAPIResponse {
    public String _id;
    public String name;
    public String category;
    public Location location;
    private String country;
    private String address;
    private String imageURL;
    private String count;
    private String about;
    private String price;
    private String total_area;
    private String living_area;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal_area() {
        return total_area;
    }

    public void setTotal_area(String total_area) {
        this.total_area = total_area;
    }

    public String getLiving_area() {
        return living_area;
    }

    public void setLiving_area(String living_area) {
        this.living_area = living_area;
    }

    public LocationAPIResponse(String _id, String name, String category, Location location, String country, String address, String imageURL, String count, String about, String price, String total_area, String living_area) {
        this._id = _id;
        this.name = name;
        this.category = category;
        this.location = location;
        this.country = country;
        this.address = address;
        this.imageURL = imageURL;
        this.count = count;
        this.about = about;
        this.price = price;
        this.total_area = total_area;
        this.living_area = living_area;
    }
}
