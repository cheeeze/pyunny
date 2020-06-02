package com.ssafy.backend.vo;

public class Sale {
    private int id;
    private int productId;
    private String name;
    private int franchiseId;
    private String type;
    private String dumImage;
    private String dumName;
    private int dumPrice;
    private Product product;

    public Sale() {
    }

    public Sale(int id, int productId, String name, int franchiseId, String type, String dumImage, String dumName,
            int dumPrice) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.franchiseId = franchiseId;
        this.type = type;
        this.dumImage = dumImage;
        this.dumName = dumName;
        this.dumPrice = dumPrice;
    }

    public Sale(int id, int productId, String name, int franchiseId, String type, String dumImage, String dumName,
            int dumPrice, Product product) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.franchiseId = franchiseId;
        this.type = type;
        this.dumImage = dumImage;
        this.dumName = dumName;
        this.dumPrice = dumPrice;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(int franchiseId) {
        this.franchiseId = franchiseId;
    }

    public String getDumImage() {
        return dumImage;
    }

    public void setDumImage(String dumImage) {
        this.dumImage = dumImage;
    }

    public String getDumName() {
        return dumName;
    }

    public void setDumName(String dumName) {
        this.dumName = dumName;
    }

    public int getDumPrice() {
        return dumPrice;
    }

    public void setDumPrice(int dumPrice) {
        this.dumPrice = dumPrice;
    }

    @Override
    public String toString() {
        return "Sale [dumImage=" + dumImage + ", dumName=" + dumName + ", dumPrice=" + dumPrice + ", franchiseId="
                + franchiseId + ", id=" + id + ", name=" + name + ", productId=" + productId + ", type=" + type + "]";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}