package com.example.myapplication.vo;

public class Sale {
    private int id;
    private int product_id;
    private String name;
    private int franchise_id;
    private String type;
    private String dum_image;
    private String dum_name;
    private int dum_price;
    private String product_name;
    private int product_price;
    private String product_image;

    public Sale(int franchise_id, String type, String product_name, int product_price, String product_image) {
        this.franchise_id = franchise_id;
        this.type = type;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
    }

    public Sale(int id, int product_id, String name, int franchise_id, String type, String dum_image, String dum_name, int dum_price, String product_name, int product_price, String product_image) {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
        this.franchise_id = franchise_id;
        this.type = type;
        this.dum_image = dum_image;
        this.dum_name = dum_name;
        this.dum_price = dum_price;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFranchise_id() {
        return franchise_id;
    }

    public void setFranchise_id(int franchise_id) {
        this.franchise_id = franchise_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDum_image() {
        return dum_image;
    }

    public void setDum_image(String dum_image) {
        this.dum_image = dum_image;
    }

    public String getDum_name() {
        return dum_name;
    }

    public void setDum_name(String dum_name) {
        this.dum_name = dum_name;
    }

    public int getDum_price() {
        return dum_price;
    }

    public void setDum_price(int dum_price) {
        this.dum_price = dum_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", name='" + name + '\'' +
                ", franchise_id=" + franchise_id +
                ", type='" + type + '\'' +
                ", dum_image='" + dum_image + '\'' +
                ", dum_name='" + dum_name + '\'' +
                ", dum_price='" + dum_price + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", product_image='" + product_image + '\'' +
                '}';
    }


}
