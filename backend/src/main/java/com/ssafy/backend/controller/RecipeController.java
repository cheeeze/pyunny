package com.ssafy.backend.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.backend.service.RecipeService;
import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.Recipe;
import com.ssafy.backend.vo.RecipeComment;
import com.ssafy.backend.vo.RecipeCommentParent;
import com.ssafy.backend.vo.RecipeLike;
import com.ssafy.backend.vo.TempKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
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
        System.out.println("recent order");
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
        System.out.println(r.toString());
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

    @GetMapping("/product_recipe/{id}")
    public ResponseEntity<List<Product>> getIngredientProduct(@PathVariable int id) throws Exception {
        List<Product> res = service.getIngredientProduct(id);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<Product>> getProductAutoComplete(@RequestParam String keyword) throws Exception {
        List<Product> res = service.getProductAutoComplete(keyword);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping("/recipe_comment")
    public ResponseEntity insertComment(@RequestBody RecipeComment rc) throws Exception {
        service.insertComment(rc);
        return new ResponseEntity<>(rc, HttpStatus.OK);
    }

    @DeleteMapping("/recipe_comment/{id}")
    public ResponseEntity deleteComment(@PathVariable int id) throws Exception {
        service.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/recipe_comment/{recipeId}")
    public ResponseEntity<List<RecipeCommentParent>> getComment(@PathVariable int recipeId) throws Exception {
        List<RecipeCommentParent> res = service.getComment(recipeId);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping("/recipe_like")
    public ResponseEntity insertRecipeLike(@RequestBody RecipeLike rl) {
        // System.out.println(r.toString());
        try {
            service.insertRecipeLike(rl);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/recipe_like")
    public ResponseEntity deleteRecipeLike(@ModelAttribute RecipeLike rl) {

        try {
            service.deleteRecipeLike(rl);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/recipe_like")
    public ResponseEntity getRecipeLike(@ModelAttribute RecipeLike rl) {

        try {
            RecipeLike res = service.getRecipeLike(rl);
            if (res != null) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/uploadFiles")
    public Object uploadFiles(@RequestBody List<MultipartFile> files) throws Exception {
        // request.setCharacterEncoding("utf-8");
        // response.setCharacterEncoding("utf-8");

        List<String> res = new ArrayList<>();
        System.out.println(files.size());

        for (int i = 0; i < files.size(); i++) {
            MultipartFile mfile = files.get(i);
            // 2. 업로드된 파일이 있다면 파일의 사이즈, 이름을 출력
            if (!mfile.isEmpty()) { // 업로드된 파일이 있다면
                System.out.println("파일의 사이즈:" + mfile.getSize());
                // System.out.println("파일의 이름:" + mfile.getOriginalFilename());
            }

            String name = mfile.getOriginalFilename();
            name = new String(name.getBytes("8859_1"), "utf-8");

            // String root = "C://Users/multicampus/SketchBook/back-sk/";
            String path = "C://upload/";
            // String path = "/home/ubuntu/project/upload/";
            /*
             * String root = request.getSession().getServletContext().getRealPath("/");
             * String path = root + "/";
             */

            // 3. 업로드된 파일을 별도의 경로로 이동

            String fileName = new TempKey().getKey(20, false) + ".jpg";
            System.out.println("fileName:" + fileName);
            File copyFile = new File(path + fileName);
            mfile.transferTo(copyFile);
            System.out.println("path:" + path);
            res.add(fileName);
        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}