package com.ssafy.backend.vo;

public class Rating {
    private int id;
    private int userId;
    private int productId;
    private int score;

    public Rating() {
    }

    public Rating(int id, int userId, int productId, int score) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Rating [id=" + id + ", productId=" + productId + ", score=" + score + ", userId=" + userId + "]";
    }

}