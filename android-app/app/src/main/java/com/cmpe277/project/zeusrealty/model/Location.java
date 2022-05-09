package com.cmpe277.project.zeusrealty.model;

public class Location {
    public Double[] coordinates;

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public Location(Double[] coordinates) {
        this.coordinates = coordinates;
    }
}
