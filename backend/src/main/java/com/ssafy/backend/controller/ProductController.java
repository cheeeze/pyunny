package com.ssafy.backend.controller;

import java.util.List;

import com.ssafy.backend.service.ProductService;
import com.ssafy.backend.vo.Favorite;
import com.ssafy.backend.vo.MainSearch;
import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.ProductComment;
import com.ssafy.backend.vo.Rating;
import com.ssafy.backend.vo.RatingCount;
import com.ssafy.backend.vo.Sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/product")
    public ResponseEntity insertProduct(@RequestBody Product p) throws Exception {
        System.out.println(p.toString());
        service.insertProduct(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sale")
    public ResponseEntity insertSale(@RequestBody Sale s) throws Exception {
        service.insertSale(s);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProduct() throws Exception {
        List<Product> res = service.getProduct();
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/sale")
    public ResponseEntity<List<Sale>> getSale() throws Exception {
        List<Sale> res = service.getSale();
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProductById(@PathVariable int id) throws Exception {
        Product res = service.getProductById(id);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/product/user")
    public ResponseEntity<List<Product>> getSimilarProduct(@ModelAttribute Product p) {
        System.out.println(p.toString());
        try {

            List<Product> res = service.getSimilarProduct(p);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/sale/{id}")
    public ResponseEntity getSaleById(@PathVariable int id) throws Exception {
        Sale res = service.getSaleById(id);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/product/name")
    public ResponseEntity<List<Product>> getProductByName(@RequestParam String keyword) throws Exception {
        List<Product> res = service.getProductByName(keyword);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/sale/name")
    public ResponseEntity<List<Sale>> getSaleByName(@RequestParam String keyword) throws Exception {
        List<Sale> res = service.getSaleByName(keyword);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> getProductBySearch(@RequestParam List<Integer> franchise,
            @RequestParam String keyword) throws Exception {
        MainSearch search = new MainSearch(keyword, franchise);
        System.out.println(search.toString());
        List<Product> res = null;
        if (search.getFranchise().size() == 0) {
            res = service.getProductByName(keyword);
        } else {
            res = service.getProductBySearch(search);
        }
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/sale/search")
    public ResponseEntity<List<Product>> getSaleBySearch(@RequestParam List<Integer> franchise,
            @RequestParam String keyword) throws Exception {
        MainSearch search = new MainSearch(keyword, franchise);
        System.out.println(search.toString());
        List<Sale> res = null;
        if (search.getFranchise().size() == 0) {
            res = service.getSaleByName(keyword);
        } else {
            res = service.getSaleBySearch(search);
        }
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PutMapping(value = "/product")
    public ResponseEntity updateProduct(@RequestBody Product p) throws Exception {
        System.out.println(p.toString());
        service.updateProduct(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/sale")
    public ResponseEntity updateSale(@RequestBody Sale p) throws Exception {
        service.updateSale(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id) throws Exception {
        service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/sale/{id}")
    public ResponseEntity deleteSale(@PathVariable int id) throws Exception {
        service.deleteSale(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/favorite")
    public ResponseEntity insertFavorite(@RequestBody Favorite f) throws Exception {
        service.insertFavorite(f);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/favorite/{id}")
    public ResponseEntity deleteFavorite(@PathVariable int id) throws Exception {
        service.deleteFavorite(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/rating")
    public ResponseEntity insertRating(@RequestBody Rating r) throws Exception {
        service.insertRating(r);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/rating/count/{id}")
    public ResponseEntity<RatingCount> getRatingcount(@PathVariable int id) throws Exception {
        RatingCount res = service.getRatingcount(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/rating")
    public ResponseEntity deleteRating(@RequestBody Rating r) throws Exception {
        service.deleteRating(r);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/rating")
    public ResponseEntity updateRating(@RequestBody Rating r) throws Exception {
        service.updateRating(r);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/product_comment")
    public ResponseEntity insertComment(@RequestBody ProductComment pc) throws Exception {
        service.insertComment(pc);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/product_comment/{id}")
    public ResponseEntity<List<ProductComment>> getCommentById(@PathVariable int id) throws Exception {
        List<ProductComment> res = service.getCommentById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/comment")
    public ResponseEntity updateComment(@RequestBody ProductComment pc) throws Exception {
        service.updateComment(pc);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity deleteComment(@PathVariable int id) throws Exception {
        service.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}