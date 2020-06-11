package com.ssafy.backend.controller;

import javax.servlet.http.HttpSession;

import com.ssafy.backend.service.UserService;
import com.ssafy.backend.vo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

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

    @PostMapping("login")
    public ResponseEntity login(HttpSession session, @RequestBody User user) {

        try {
            System.out.println(user.toString());
            user = service.login(user);
            session.setAttribute("user", user);
            System.out.println(user.toString());
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
    public ResponseEntity updateUser(User user) {
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

}