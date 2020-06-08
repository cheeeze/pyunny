package com.ssafy.backend.vo;

import java.util.List;

public class Recipe {
    private int id;
    private int userId;
    private String ingredient;
    private List<Integer> ingredientProduct;
    private String date;
    private String title;
    private String content;

    public Recipe() {
    }

    public Recipe(int id, int userId, String ingredient, String date, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.ingredient = ingredient;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public Recipe(int id, int userId, String ingredient, List<Integer> ingredientProduct, String date, String title,
            String content) {
        this.id = id;
        this.userId = userId;
        this.ingredient = ingredient;
        this.ingredientProduct = ingredientProduct;
        this.date = date;
        this.title = title;
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

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public List<Integer> getIngredientProduct() {
        return ingredientProduct;
    }

    public void setIngredientProduct(List<Integer> ingredientProduct) {
        this.ingredientProduct = ingredientProduct;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Recipe [content=" + content + ", date=" + date + ", id=" + id + ", ingredient=" + ingredient
                + ", ingredientProduct=" + ingredientProduct + ", title=" + title + ", userId=" + userId + "]";
    }

}