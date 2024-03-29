package com.ssafy.backend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ssafy.backend.service.UserService;
import com.ssafy.backend.vo.Favorite;
import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.Recipe;
import com.ssafy.backend.vo.RecipeComment;
import com.ssafy.backend.vo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*")
@Api(tags = { "USER" }, description = "SSAFY API (Test)")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("{id}")
    public ResponseEntity mypage(@PathVariable("id") int id) {
        try {
            User user = service.selectOne(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "웹로그인", notes = "로그인이 성공하면 세션에 user라는 key로 User객체를 저장한다")
    @PostMapping("login")
    public ResponseEntity login(HttpSession session, @RequestBody User user) {

        try {
            System.out.println(user.toString());
            user = service.login(user);
            // session.setAttribute("user", user);
            System.out.println(user.toString());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "앱로그인", notes = "User 객체로만 로그인 확인")
    @PostMapping("applogin")
    public ResponseEntity applogin(@RequestBody User user) {
        try {
            user = service.login(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("user")
    public ResponseEntity insertUser(@RequestBody User user) {
        try {
            service.insertUser(user);
        } catch (Exception e) {
            System.out.println(e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("user")
    public ResponseEntity deleteUser(String userId) {
        try {
            service.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("user")
    public ResponseEntity updateUser(@RequestBody User user) {
        System.out.println(user.toString());
        try {
            service.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("nicknamecheck/{nickname}")
    public ResponseEntity duplicateNickname(@PathVariable("nickname") String nickname) {
        try {
            int check = service.duplicateNickname(nickname);
            return new ResponseEntity<>(check, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    };

    @GetMapping("/recipe/{id}")
    public ResponseEntity<List<Recipe>> getUserRecipe(@PathVariable int id) {
        try {
            List<Recipe> res = service.getUserRecipe(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/recipe_like/{id}")
    public ResponseEntity<List<Recipe>> getUserRecipeLike(@PathVariable int id) {
        try {
            List<Recipe> res = service.getUserRecipeLike(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/recipe_comment/{id}")
    public ResponseEntity<List<RecipeComment>> getUserRecipeComment(@PathVariable int id) {
        try {
            List<RecipeComment> res = service.getUserRecipeComment(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/favorite/{id}")
    public ResponseEntity<List<Product>> getUserFavorite(@PathVariable int id) {
        try {
            List<Product> res = service.getUserFavorite(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/favorite")
    public ResponseEntity<List<Product>> deleteUserFavorite(@ModelAttribute Favorite f) {
        try {
            service.deleteUserFavorite(f);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}