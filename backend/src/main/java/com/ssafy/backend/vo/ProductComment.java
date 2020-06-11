
package com.ssafy.backend.vo;

public class ProductComment {
    private int id;
    private int userId;
    private String nickname;
    private int productId;
    private String content;

    public ProductComment() {
    }

    public ProductComment(int id, int userId, int productId, String content) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.content = content;
    }

    public ProductComment(int id, int userId, String nickname, int productId, String content) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.productId = productId;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "ProductComment [content=" + content + ", id=" + id + ", nickname=" + nickname + ", productId="
                + productId + ", userId=" + userId + "]";
    }

}