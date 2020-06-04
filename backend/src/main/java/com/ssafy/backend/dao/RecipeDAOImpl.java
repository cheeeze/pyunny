package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.Recipe;
import com.ssafy.backend.vo.RecipeComment;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeDAOImpl implements RecipeDAO {
    private final String ns = "RecipeMapper.";

    @Autowired
    private SqlSession session;

    @Override
    public List<Recipe> getRecipePopularOrdered() throws Exception {
        return session.selectList(ns + "getRecipePopularOrdered");
    }

    @Override
    public List<Recipe> getRecipeRecentOrdered() throws Exception {
        return session.selectList(ns + "getRecipeRecentOrdered");
    }

    @Override
    public List<Recipe> getRecipeBySearch(String keyword) throws Exception {
        return session.selectList(ns + "getRecipeBySearch", keyword);
    }

    @Override
    public Recipe getRecipeById(int id) throws Exception {
        return session.selectOne(ns + "getRecipeById", id);
    }

    @Override
    public void insertRecipe(Recipe r) throws Exception {
        session.insert(ns + "insertRecipe", r);

    }

    @Override
    public void updateRecipe(Recipe r) throws Exception {
        session.update(ns + "updateRecipe", r);

    }

    @Override
    public void deleteRecipe(int id) throws Exception {
        session.delete(ns + "deleteRecipe", id);

    }

    @Override
    public List<Product> getProductAutoComplete(String keyword) throws Exception {
        return session.selectList(ns + "getProductAutoComplete", keyword);
    }

    @Override
    public void insertComment(RecipeComment rc) throws Exception {
        session.insert(ns + "insertComment", rc);

    }

    @Override
    public List<RecipeComment> getComment(int recipeId) throws Exception {
        System.out.println("recipeId:" + recipeId);
        return session.selectList(ns + "getComment", recipeId);
    }

    @Override
    public void deleteComment(int id) throws Exception {
        session.delete(ns + "deleteComment", id);

    }

    @Override
    public void insertIngredient(Recipe r) throws Exception {
        session.insert(ns + "insertIngredient", r);

    }

}