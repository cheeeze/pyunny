package com.ssafy.backend.controller;

import java.util.List;

import com.ssafy.backend.service.RecipeService;
import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.Recipe;
import com.ssafy.backend.vo.RecipeComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService service;

    @GetMapping("/recipe_popular")
    public ResponseEntity<List<Recipe>> getRecipePopularOrdered() throws Exception {
        List<Recipe> res = service.getRecipePopularOrdered();
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/recipe_recent")
    public ResponseEntity<List<Recipe>> getRecipeRecentOrdered() throws Exception {
        List<Recipe> res = service.getRecipeRecentOrdered();
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/recipe")
    public ResponseEntity<List<Recipe>> getRecipeBySearch(@RequestParam String keyword) throws Exception {
        List<Recipe> res = service.getRecipeBySearch(keyword);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable int id) throws Exception {
        Recipe res = service.getRecipeById(id);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping("/recipe")
    public ResponseEntity insertRecipe(@RequestBody Recipe r) throws Exception {
        service.insertRecipe(r);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/recipe")
    public ResponseEntity updateRecipe(@RequestBody Recipe r) throws Exception {
        service.updateRecipe(r);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity deleteRecipe(@PathVariable int id) throws Exception {
        service.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<Product>> getProductAutoComplete(@RequestParam String keyword) throws Exception {
        List<Product> res = service.getProductAutoComplete(keyword);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping("/recipe_comment")
    public ResponseEntity insertComment(@RequestBody RecipeComment rc) throws Exception {
        service.insertComment(rc);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/recipe_comment/{id}")
    public ResponseEntity deleteComment(@PathVariable int id) throws Exception {
        service.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/recipe_comment/{recipeId}")
    public ResponseEntity<List<Product>> getComment(@PathVariable int recipeId) throws Exception {
        List<RecipeComment> res = service.getComment(recipeId);
        return new ResponseEntity(res, HttpStatus.OK);
    }

}