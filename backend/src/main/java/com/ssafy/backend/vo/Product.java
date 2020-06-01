package com.ssafy.backend.vo;

public class Product {
    private int id;
    private String name;
    private int franchiseId;
    private int price;
    private String category;
    private String image;

    public Product() {
    }

    public Product(int id, String name, int franchiseId, int price, String category, String image) {
        this.id = id;
        this.name = name;
        this.franchiseId = franchiseId;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(int franchiseId) {
        this.franchiseId = franchiseId;
    }

    @Override
    public String toString() {
        return "Product [category=" + category + ", franchiseId=" + franchiseId + ", id=" + id + ", image=" + image
                + ", name=" + name + ", price=" + price + "]";
    }

}