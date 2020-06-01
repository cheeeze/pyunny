package com.ssafy.backend.vo;

public class RecipeComment {
    private int id;
    private int userId;
    private int recipeId;
    private String date;
    private String content;
    private int parentId;

    public RecipeComment() {
    }

    public RecipeComment(int id, int userId, int recipeId, String date, String content, int parentId) {
        this.id = id;
        this.userId = userId;
        this.recipeId = recipeId;
        this.date = date;
        this.content = content;
        this.parentId = parentId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "RecipeComment [content=" + content + ", date=" + date + ", id=" + id + ", parentId=" + parentId
                + ", recipeId=" + recipeId + ", userId=" + userId + "]";
    }

}