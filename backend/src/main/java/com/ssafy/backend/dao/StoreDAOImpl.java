package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.MapSearch;
import com.ssafy.backend.vo.Store;
import com.ssafy.backend.vo.StoreProduct;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StoreDAOImpl implements StoreDAO {
    private final String ns = "StoreMapper.";

    @Autowired
    private SqlSession session;

    @Override
    public List<Store> getStoreNear(MapSearch search) throws Exception {
        System.out.println(search.toString());
        return session.selectList(ns + "getStoreNear", search);
    }

    @Override
    public List<StoreProduct> getStoreProductById(MapSearch search) throws Exception {
        return session.selectList(ns + "getStoreProductById", search);
    }

}