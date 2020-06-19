package com.ssafy.backend.vo;

import java.util.List;

public class GiftInsert {
    private int userId;
    private List<String> imageUrl;

    public GiftInsert() {
    }

    public GiftInsert(int userId, List<String> imageUrl) {
        this.userId = userId;
        this.imageUrl = imageUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "GiftInsert [imageUrl=" + imageUrl + ", userId=" + userId + "]";
    }

}