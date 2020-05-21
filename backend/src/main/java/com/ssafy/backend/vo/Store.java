package com.ssafy.backend.vo;

public class Store {
    private int id;
    private int franchiseId;
    private String storeName;
    private float latitude;
    private float longitude;
    private String address;
    private String tel;
    private int isatm;
    private int islottery;
    private int isdelivery;
    private int ismedicine;
    private int isfulltime;

    public Store() {
    }

    public Store(int id, int franchiseId, String storeName, float latitude, float longitude, String address, String tel,
            int isatm, int islottery, int isdelivery, int ismedicine, int isfulltime) {
        this.id = id;
        this.franchiseId = franchiseId;
        this.storeName = storeName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.tel = tel;
        this.isatm = isatm;
        this.islottery = islottery;
        this.isdelivery = isdelivery;
        this.ismedicine = ismedicine;
        this.isfulltime = isfulltime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getIsatm() {
        return isatm;
    }

    public void setIsatm(int isatm) {
        this.isatm = isatm;
    }

    public int getIslottery() {
        return islottery;
    }

    public void setIslottery(int islottery) {
        this.islottery = islottery;
    }

    public int getIsdelivery() {
        return isdelivery;
    }

    public void setIsdelivery(int isdelivery) {
        this.isdelivery = isdelivery;
    }

    public int getIsmedicine() {
        return ismedicine;
    }

    public void setIsmedicine(int ismedicine) {
        this.ismedicine = ismedicine;
    }

    public int getIsfulltime() {
        return isfulltime;
    }

    public void setIsfulltime(int isfulltime) {
        this.isfulltime = isfulltime;
    }

    public int getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(int franchiseId) {
        this.franchiseId = franchiseId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "Store [address=" + address + ", franchiseId=" + franchiseId + ", id=" + id + ", isatm=" + isatm
                + ", isdelivery=" + isdelivery + ", isfulltime=" + isfulltime + ", islottery=" + islottery
                + ", ismedicine=" + ismedicine + ", latitude=" + latitude + ", longitude=" + longitude + ", storeName="
                + storeName + ", tel=" + tel + "]";
    }

}