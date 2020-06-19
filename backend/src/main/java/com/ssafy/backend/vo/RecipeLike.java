package com.ssafy.backend.vo;

public class RecipeLike {
    private int id;
    private int userId;
    private int recipeId;

    public RecipeLike() {
    }

    public RecipeLike(int id, int userId, int recipeId) {
        this.id = id;
        this.userId = userId;
        this.recipeId = recipeId;
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

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "RecipeLike [id=" + id + ", recipeId=" + recipeId + ", userId=" + userId + "]";
    }

}