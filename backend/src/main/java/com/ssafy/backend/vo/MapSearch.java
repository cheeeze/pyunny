package com.ssafy.backend.vo;

public class MapSearch {
    private float latitude;
    private float longitude;
    private int distance;
    private String keyword;
    private int storeId;

    public MapSearch() {
    }

    public MapSearch(float latitude, float longitude, int distance, String keyword) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.keyword = keyword;
    }

    public MapSearch(float latitude, float longitude, int distance, String keyword, int storeId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.keyword = keyword;
        this.storeId = storeId;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "MapSearch [distance=" + distance + ", keyword=" + keyword + ", latitude=" + latitude + ", longitude="
                + longitude + ", storeId=" + storeId + "]";
    }

}