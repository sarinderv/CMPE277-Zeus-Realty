package com.cmpe277.project.zeusrealty.model;

import java.util.List;

public class LocationAPIResponse {
    public String _id;
    public String name;
    public String category;
    public Location location;
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

    public LocationAPIResponse(String _id, String name, String category, Location location) {
        this._id = _id;
        this.name = name;
        this.category = category;
        this.location = location;
    }
}
