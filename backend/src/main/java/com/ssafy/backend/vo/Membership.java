package com.ssafy.backend.vo;

public class Membership {

    private int id;
    private int userId;
    private String type;
    private String number;

    public Membership(int id, int userId, String type, String number) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.number = number;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Membership [id=" + id + ", number=" + number + ", type=" + type + ", userId=" + userId + "]";
    }
    
}