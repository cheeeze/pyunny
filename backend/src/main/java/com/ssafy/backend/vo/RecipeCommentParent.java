package com.ssafy.backend.vo;

import java.util.List;

/**
 * RecipeCommentChild
 */

public class RecipeCommentParent {
    private int id;
    private int userId;
    private int recipeId;
    private String date;
    private String content;

    private List<RecipeComment> childComment;

    public RecipeCommentParent() {
    }

    public RecipeCommentParent(int id, int userId, int recipeId, String date, String content,
            List<RecipeComment> childComment) {
        this.id = id;
        this.userId = userId;
        this.recipeId = recipeId;
        this.date = date;
        this.content = content;
        this.childComment = childComment;
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

    public List<RecipeComment> getChildComment() {
        return childComment;
    }

    public void setChildComment(List<RecipeComment> childComment) {
        this.childComment = childComment;
    }

    @Override
    public String toString() {
        return "RecipeCommentParent [childComment=" + childComment + ", content=" + content + ", date=" + date + ", id="
                + id + ", recipeId=" + recipeId + ", userId=" + userId + "]";
    }

}