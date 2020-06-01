package com.ssafy.backend.vo;

public class ProductRecipe {
    private int recipeId;
    private int productId;

    public ProductRecipe() {
    }

    public ProductRecipe(int recipeId, int productId) {
        this.recipeId = recipeId;
        this.productId = productId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductRecipe [productId=" + productId + ", recipeId=" + recipeId + "]";
    }

}