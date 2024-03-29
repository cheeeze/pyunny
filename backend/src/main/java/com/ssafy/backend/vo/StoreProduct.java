package com.ssafy.backend.vo;

public class StoreProduct {
    private int id;
    private int storeId;
    private int productId;
    private String name;
    private String image;
    private String description;
    private int price;
    private int stockAmount;

    public StoreProduct() {
    }

    public StoreProduct(int id, int storeId, int productId, String name, String description, int price,
            int stockAmount) {
        this.id = id;
        this.storeId = storeId;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
    }

    public StoreProduct(int id, int storeId, int productId, String name, String image, String description, int price,
            int stockAmount) {
        this.id = id;
        this.storeId = storeId;
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "StoreProduct [description=" + description + ", id=" + id + ", image=" + image + ", name=" + name
                + ", price=" + price + ", productId=" + productId + ", stockAmount=" + stockAmount + ", storeId="
                + storeId + "]";
    }

}