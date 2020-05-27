package com.ssafy.backend.vo;

import java.io.Serializable;

public class Store implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private int franchiseId;
    private String franchiseName;
    private String storeName;
    private float latitude;
    private float longitude;
    private String address;
    private String city;
    private String tel;
    private int isatm;
    private int islottery;
    private int isdelivery;
    private int ismedicine;
    private int isfulltime;
    private String logoUrl;
    private String deliveryBegin;
    private String deliveryEnd;

    public Store() {
    }

    public Store(int id, int franchiseId, String franchiseName, String storeName, float latitude, float longitude,
            String address, String city, String tel, int isatm, int islottery, int isdelivery, int ismedicine,
            int isfulltime, String logoUrl, String deliveryBegin, String deliveryEnd) {
        this.id = id;
        this.franchiseId = franchiseId;
        this.franchiseName = franchiseName;
        this.storeName = storeName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.tel = tel;
        this.isatm = isatm;
        this.islottery = islottery;
        this.isdelivery = isdelivery;
        this.ismedicine = ismedicine;
        this.isfulltime = isfulltime;
        this.logoUrl = logoUrl;
        this.deliveryBegin = deliveryBegin;
        this.deliveryEnd = deliveryEnd;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getDeliveryBegin() {
        return deliveryBegin;
    }

    public void setDeliveryBegin(String deliveryBegin) {
        this.deliveryBegin = deliveryBegin;
    }

    public String getDeliveryEnd() {
        return deliveryEnd;
    }

    public void setDeliveryEnd(String deliveryEnd) {
        this.deliveryEnd = deliveryEnd;
    }

    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Store [address=" + address + ", city=" + city + ", deliveryBegin=" + deliveryBegin + ", deliveryEnd="
                + deliveryEnd + ", franchiseId=" + franchiseId + ", franchiseName=" + franchiseName + ", id=" + id
                + ", isatm=" + isatm + ", isdelivery=" + isdelivery + ", isfulltime=" + isfulltime + ", islottery="
                + islottery + ", ismedicine=" + ismedicine + ", latitude=" + latitude + ", logoUrl=" + logoUrl
                + ", longitude=" + longitude + ", storeName=" + storeName + ", tel=" + tel + "]";
    }

}