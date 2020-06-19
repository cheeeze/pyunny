package com.ssafy.backend.vo;

public class Gifticon {
    private int id;
    private int userId;
    private String name;
    private String startDate;
    private String endDate;
    private int franchise;
    private String number;
    private String image;

    public Gifticon() {
    }

    public Gifticon(int id, int userId, String name, String startDate, String endDate, int franchise, String number,
            String image) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.franchise = franchise;
        this.number = number;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getFranchise() {
        return franchise;
    }

    public void setFranchise(int franchise) {
        this.franchise = franchise;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Gifticon [endDate=" + endDate + ", franchise=" + franchise + ", id=" + id + ", image=" + image
                + ", name=" + name + ", number=" + number + ", startDate=" + startDate + ", userId=" + userId + "]";
    }

}