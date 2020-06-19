package com.ssafy.backend.controller;

import java.util.List;

import com.ssafy.backend.service.GifticonService;
import com.ssafy.backend.vo.GiftInsert;
import com.ssafy.backend.vo.Gifticon;

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
@RequestMapping("gift")
@CrossOrigin(origins = "*")
@Api(tags = { "GIFTICON" }, description = "SSAFY API (Test)")
public class GifticonController {

    @Autowired
    private GifticonService service;

    @GetMapping("all")
    public ResponseEntity getAllGifticon() {
        try {
            List<Gifticon> list = service.getAllGifticon();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity getGifticonById(@PathVariable("id") int id) {
        try {
            Gifticon gifticon = service.getGifticonById(id);
            return new ResponseEntity<>(gifticon, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*
     * @PostMapping("gift") public ResponseEntity insertGifticon(@RequestBody
     * Gifticon gifticon) { try { service.insertGifticon(gifticon); return new
     * ResponseEntity<>(HttpStatus.OK); } catch (Exception e) {
     * System.out.println(e); } return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     * }
     */
    @PostMapping("gift")
    public ResponseEntity insertGifticon(@RequestBody GiftInsert gifticon) {
        System.out.println(gifticon.toString());
        System.out.println(gifticon.getUserId());
        for (String s : gifticon.getImageUrl()) {
            System.out.println(s);
        }
        // System.out.println(gifticon.toString());
        try {
            service.insertGifticon(gifticon);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Gifticon>> getGifticonUserId(@PathVariable int id) {
        try {
            List<Gifticon> res = service.getGifticonUserId(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/used/{id}")
    public ResponseEntity<List<Gifticon>> getUsedGifticonUserId(@PathVariable int id) {
        try {
            List<Gifticon> res = service.getUsedGifticonUserId(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("")
    public ResponseEntity deleteGifticon(@RequestParam List<Integer> id) {
        try {
            service.deleteGifticon(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("gift")
    public ResponseEntity updateGifticon(@RequestParam List<Integer> id) {
        try {
            service.updateGifticon(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}