package com.ssafy.backend.controller;

import java.util.List;

import com.ssafy.backend.service.StoreService;
import com.ssafy.backend.vo.MapSearch;
import com.ssafy.backend.vo.MapSearchResult;
import com.ssafy.backend.vo.Store;
import com.ssafy.backend.vo.StoreProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class StoreController {

    @Autowired
    private StoreService service;

    @GetMapping("/store")
    public ResponseEntity<List<Store>> getStoreNear(@ModelAttribute MapSearch search) throws Exception {
        System.out.println("getStoreNear");
        System.out.println(search.toString());
        List<Store> res = service.getStoreNear(search);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/store/atm")
    public ResponseEntity<List<Store>> getAtmStoreNear(@ModelAttribute MapSearch search) throws Exception {
        System.out.println("getAtmStoreNear");
        System.out.println(search.toString());
        List<Store> res = service.getAtmStoreNear(search);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/store/medicine")
    public ResponseEntity<List<Store>> getMedicineStoreNear(@ModelAttribute MapSearch search) throws Exception {
        System.out.println("getMedicineStoreNear");
        System.out.println(search.toString());
        List<Store> res = service.getMedicineStoreNear(search);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/store/delivery")
    public ResponseEntity<List<Store>> getDeliveryStoreNear(@ModelAttribute MapSearch search) throws Exception {
        System.out.println("getDeliveryStoreNear");
        System.out.println(search.toString());
        List<Store> res = service.getDeliveryStoreNear(search);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/store/24hour")
    public ResponseEntity<List<Store>> get24hourStoreNear(@ModelAttribute MapSearch search) throws Exception {
        System.out.println("get24hourStoreNear");
        System.out.println(search.toString());
        List<Store> res = service.get24hourStoreNear(search);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/store_product")
    public ResponseEntity<List<MapSearchResult>> getStoreProductBySearch(@ModelAttribute MapSearch search)
            throws Exception {
        System.out.println(search.toString());
        List<MapSearchResult> res = service.getStoreProductBySearch(search);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/store_product/{storeId}")
    public ResponseEntity<List<StoreProduct>> getStoreProductById(@PathVariable int storeId) throws Exception {
        System.out.println("get store product By store Id");
        List<StoreProduct> res = service.getStoreProductById(storeId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}