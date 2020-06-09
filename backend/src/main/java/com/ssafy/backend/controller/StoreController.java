package com.ssafy.backend.controller;

import java.util.List;

import com.ssafy.backend.service.StoreService;
import com.ssafy.backend.vo.MapSearch;
import com.ssafy.backend.vo.Store;
import com.ssafy.backend.vo.StoreProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class StoreController {

    @Autowired
    private StoreService service;

    @GetMapping("/store")
    public ResponseEntity<List<Store>> getStoreNear(@ModelAttribute MapSearch search) throws Exception {
        System.out.println(search.toString());
        List<Store> res = service.getStoreNear(search);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/store_product")
    public ResponseEntity<List<StoreProduct>> getStoreProductById(@ModelAttribute MapSearch search) throws Exception {
        List<StoreProduct> res = service.getStoreProductById(search);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}