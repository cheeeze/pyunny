package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.Favorite;
import com.ssafy.backend.vo.MainSearch;
import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.ProductComment;
import com.ssafy.backend.vo.Rating;
import com.ssafy.backend.vo.RatingCount;
import com.ssafy.backend.vo.Recipe;
import com.ssafy.backend.vo.Sale;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final String ns = "ProductMapper.";

    @Autowired
    private SqlSession session;

    @Override
    public void insertProduct(Product p) throws Exception {
        session.insert(ns + "insertProduct", p);
    }

    @Override
    public void insertSale(Sale s) throws Exception {
        session.insert(ns + "insertSale", s);

    }

    @Override
    public List<Product> getProduct() throws Exception {
        return session.selectList(ns + "getProduct");
    }

    @Override
    public List<Sale> getSale() throws Exception {
        return session.selectList(ns + "getSale");
    }

    @Override
    public Product getProductById(int id) throws Exception {
        return session.selectOne(ns + "getProductById", id);
    }

    @Override
    public Sale getSaleById(int id) throws Exception {
        return session.selectOne(ns + "getSaleById", id);
    }

    @Override
    public List<Product> getProductByName(String keyword) throws Exception {
        return session.selectList(ns + "getProductByName", keyword);
    }

    @Override
    public List<Sale> getSaleByName(String keyword) throws Exception {
        return session.selectList(ns + "getSaleByName", keyword);
    }

    @Override
    public List<Product> getProductBySearch(MainSearch search) throws Exception {
        return session.selectList(ns + "getProductBySearch", search);
    }

    @Override
    public List<Sale> getSaleBySearch(MainSearch search) throws Exception {
        return session.selectList(ns + "getSaleBySearch", search);
    }

    @Override
    public void updateProduct(Product p) throws Exception {
        session.update(ns + "updateProduct", p);

    }

    @Override
    public void updateSale(Sale s) throws Exception {
        session.update(ns + "updateSale", s);

    }

    @Override
    public void deleteProduct(int id) throws Exception {
        session.delete(ns + "deleteProduct", id);

    }

    @Override
    public void deleteSale(int id) throws Exception {
        session.delete(ns + "deleteSale", id);

    }

    @Override
    public void insertFavorite(Favorite f) {
        session.insert(ns + "insertFavorite", f);

    }

    @Override
    public void deleteFavorite(int id) throws Exception {
        session.delete(ns + "deleteFavorite", id);

    }

    @Override
    public void insertRating(Rating r) {
        session.insert(ns + "insertRating", r);

    }

    @Override
    public void deleteRating(Rating r) {
        session.delete(ns + "deleteRating", r);

    }

    @Override
    public void updateRating(Rating r) {
        session.update(ns + "updateRating", r);

    }

    @Override
    public void insertComment(ProductComment pc) throws Exception {
        session.insert(ns + "insertComment", pc);

    }

    @Override
    public List<ProductComment> getCommentById(int id) {
        return session.selectList(ns + "getCommentById", id);
    }

    @Override
    public void updateComment(ProductComment pc) throws Exception {
        session.update(ns + "updateComment", pc);

    }

    @Override
    public void deleteComment(int id) throws Exception {
        session.delete(ns + "deleteComment", id);

    }

    @Override
    public RatingCount getRatingcount(int id) throws Exception {
        return session.selectOne(ns + "getRatingcount", id);
    }

    @Override
    public List<Product> getSimilarProduct(Product p) {
        return session.selectList(ns + "getSimilarProduct", p);
    }

    @Override
    public Rating getRating(Rating r) {
        return session.selectOne(ns + "getRating", r);
    }

    @Override
    public Favorite getFavorite(Favorite f) {
        return session.selectOne(ns + "getFavorite", f);
    }

    @Override
    public List<Recipe> getUsedRecipe(int id) {
        return session.selectList(ns + "getUsedRecipe", id);
    }

}