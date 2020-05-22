package com.ssafy.backend.service;

import java.util.List;

import com.ssafy.backend.dao.ProductDAO;
import com.ssafy.backend.vo.MainSearch;
import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.Sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO dao;

    @Override
    public void insertProduct(Product p) throws Exception {
        dao.insertProduct(p);

    }

    @Override
    public void insertSale(Sale s) throws Exception {
        dao.insertSale(s);

    }

    @Override
    public List<Product> getProduct() throws Exception {
        return dao.getProduct();
    }

    @Override
    public List<Sale> getSale() throws Exception {
        return dao.getSale();
    }

    @Override
    public Product getProductById(int id) throws Exception {
        return dao.getProductById(id);
    }

    @Override
    public Sale getSaleById(int id) throws Exception {
        return dao.getSaleById(id);
    }

    @Override
    public List<Product> getProductByName(String keyword) throws Exception {
        return dao.getProductByName(keyword);
    }

    @Override
    public List<Sale> getSaleByName(String keyword) throws Exception {
        return dao.getSaleByName(keyword);
    }

    @Override
    public List<Product> getProductBySearch(MainSearch search) throws Exception {
        return dao.getProductBySearch(search);
    }

    @Override
    public List<Sale> getSaleBySearch(MainSearch search) throws Exception {
        return dao.getSaleBySearch(search);
    }

    @Override
    public void updateProduct(Product p) throws Exception {
        dao.updateProduct(p);

    }

    @Override
    public void updateSale(Sale s) throws Exception {
        dao.updateSale(s);

    }

    @Override
    public void deleteProduct(Product p) throws Exception {
        dao.deleteProduct(p);

    }

    @Override
    public void deleteSale(Sale s) throws Exception {
        dao.deleteSale(s);

    }

}