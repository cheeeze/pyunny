package com.ssafy.backend.service;

import java.util.List;

import com.ssafy.backend.vo.Favorite;
import com.ssafy.backend.vo.MainSearch;
import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.ProductComment;
import com.ssafy.backend.vo.Rating;
import com.ssafy.backend.vo.Sale;

public interface ProductService {
    void insertProduct(Product p) throws Exception;

    void insertSale(Sale s) throws Exception;

    List<Product> getProduct() throws Exception;

    List<Sale> getSale() throws Exception;

    Product getProductById(int id) throws Exception;

    Sale getSaleById(int id) throws Exception;

    List<Product> getProductByName(String keyword) throws Exception;

    List<Sale> getSaleByName(String keyword) throws Exception;

    List<Product> getProductBySearch(MainSearch search) throws Exception;

    List<Sale> getSaleBySearch(MainSearch search) throws Exception;

    void updateProduct(Product p) throws Exception;

    void updateSale(Sale s) throws Exception;

    void deleteProduct(int id) throws Exception;

    void deleteSale(int id) throws Exception;

    void insertFavorite(Favorite f) throws Exception;

    void deleteFavorite(int id) throws Exception;

    void insertRating(Rating r) throws Exception;

    void deleteRating(int id) throws Exception;

    void updateRating(Rating r) throws Exception;

    void insertComment(ProductComment pc) throws Exception;

    List<ProductComment> getCommentById(int id) throws Exception;

    void updateComment(ProductComment pc) throws Exception;

    void deleteComment(int id) throws Exception;
}