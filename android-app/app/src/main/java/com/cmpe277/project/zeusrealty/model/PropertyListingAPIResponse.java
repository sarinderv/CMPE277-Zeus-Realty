package com.cmpe277.project.zeusrealty.model;

public class PropertyListingAPIResponse {
    public int id;
    public String name;
    public String category;
    public float x_coordinate;
    public float y_coordinate;
    public int price;
    public int total_area;
    public int living_area;
    public String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(float x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public float getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(float y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal_area() {
        return total_area;
    }

    public void setTotal_area(int total_area) {
        this.total_area = total_area;
    }

    public int getLiving_area() {
        return living_area;
    }

    public void setLiving_area(int living_area) {
        this.living_area = living_area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PropertyListingAPIResponse(int id, String name, String category, int price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
