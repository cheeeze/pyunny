package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.MapSearch;
import com.ssafy.backend.vo.MapSearchResult;
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
    public List<Store> getAtmStoreNear(MapSearch search) throws Exception {
        return session.selectList(ns + "getAtmStoreNear", search);
    }

    @Override
    public List<Store> getMedicineStoreNear(MapSearch search) throws Exception {
        return session.selectList(ns + "getMedicineStoreNear", search);
    }

    @Override
    public List<Store> getDeliveryStoreNear(MapSearch search) throws Exception {
        return session.selectList(ns + "getDeliveryStoreNear", search);
    }

    @Override
    public List<Store> get24hourStoreNear(MapSearch search) throws Exception {
        return session.selectList(ns + "get24hourStoreNear", search);
    }

    @Override
    public List<MapSearchResult> getStoreProductBySearch(MapSearch search) throws Exception {
        return session.selectList(ns + "getStoreProductBySearch", search);
    }

    @Override
    public List<StoreProduct> getStoreProductById(int storeId) throws Exception {
        return session.selectList(ns + "getStoreProductById", storeId);
    }

}