package com.ssafy.backend.service;

import java.util.List;

import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.Recipe;
import com.ssafy.backend.vo.RecipeComment;

public interface RecipeService {
    List<Recipe> getRecipePopularOrdered() throws Exception;

    List<Recipe> getRecipeRecentOrdered() throws Exception;

    List<Recipe> getRecipeBySearch(String keyword) throws Exception;

    Recipe getRecipeById(int id) throws Exception;

    void insertRecipe(Recipe r) throws Exception;

    void updateRecipe(Recipe r) throws Exception;

    void deleteRecipe(int id) throws Exception;

    List<Product> getProductAutoComplete(String keyword) throws Exception;

    void insertComment(RecipeComment rc) throws Exception;

    List<RecipeComment> getComment(int recipeId) throws Exception;

    void deleteComment(int id) throws Exception;
}