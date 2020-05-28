
package com.ssafy.backend.vo;

public class ProductComment {
    private int id;
    private int userId;
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

    @Override
    public String toString() {
        return "ProductComment [content=" + content + ", id=" + id + ", productId=" + productId + ", userId=" + userId
                + "]";
    }

}