package com.ssafy.backend.vo;

import java.util.List;

public class MapSearch {
    private double latitude;
    private double longitude;
    private float distance;
    private String keyword;
    private List<Integer> store;

    public MapSearch() {
    }

    public MapSearch(double latitude, double longitude, float distance) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public MapSearch(double latitude, double longitude, float distance, String keyword, List<Integer> store) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.keyword = keyword;
        this.store = store;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getStore() {
        return store;
    }

    public void setStore(List<Integer> store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "MapSearch [distance=" + distance + ", keyword=" + keyword + ", latitude=" + latitude + ", longitude="
                + longitude + ", store=" + store + "]";
    }

}