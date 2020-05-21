
package com.ssafy.backend.vo;

public class Favorite {
    private int id;
    private int userId;
    private int productId;
    private String date;

    public Favorite() {
    }

    public Favorite(int id, int userId, int productId, String date) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.date = date;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Favorite [date=" + date + ", id=" + id + ", productId=" + productId + ", userId=" + userId + "]";
    }

}