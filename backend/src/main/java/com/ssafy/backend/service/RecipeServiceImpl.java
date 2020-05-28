package com.ssafy.backend.service;

import java.util.List;

import com.ssafy.backend.dao.RecipeDAO;
import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.Recipe;
import com.ssafy.backend.vo.RecipeComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeDAO dao;

    @Override
    public List<Recipe> getRecipePopularOrdered() throws Exception {
        return dao.getRecipePopularOrdered();
    }

    @Override
    public List<Recipe> getRecipeRecentOrdered() throws Exception {
        return dao.getRecipeRecentOrdered();
    }

    @Override
    public List<Recipe> getRecipeBySearch(String keyword) throws Exception {
        return dao.getRecipeBySearch(keyword);
    }

    @Override
    public Recipe getRecipeById(int id) throws Exception {
        return dao.getRecipeById(id);
    }

    @Override
    public void insertRecipe(Recipe r) throws Exception {
        dao.insertRecipe(r);

    }

    @Override
    public void updateRecipe(Recipe r) throws Exception {
        dao.updateRecipe(r);

    }

    @Override
    public void deleteRecipe(int id) throws Exception {
        dao.deleteRecipe(id);

    }

    @Override
    public List<Product> getProductAutoComplete(String keyword) throws Exception {
        return dao.getProductAutoComplete(keyword);
    }

    @Override
    public void insertComment(RecipeComment rc) throws Exception {
        dao.insertComment(rc);

    }

    @Override
    public List<RecipeComment> getComment(int recipeId) throws Exception {
        System.out.println("recipeId:" + recipeId);
        return dao.getComment(recipeId);
    }

    @Override
    public void deleteComment(int id) throws Exception {
        dao.deleteComment(id);

    }

}