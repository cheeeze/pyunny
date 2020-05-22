package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.MainSearch;
import com.ssafy.backend.vo.Product;
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
    public void deleteProduct(Product p) throws Exception {
        session.delete(ns + "deleteProduct", p);

    }

    @Override
    public void deleteSale(Sale s) throws Exception {
        session.delete(ns + "deleteSale", s);

    }

}