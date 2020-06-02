package com.ssafy.backend.service;

import java.util.List;

import com.ssafy.backend.vo.MapSearch;
import com.ssafy.backend.vo.Store;
import com.ssafy.backend.vo.StoreProduct;

public interface StoreService {
    List<Store> getStoreNear(MapSearch search) throws Exception;

    List<StoreProduct> getStoreProductById(MapSearch search) throws Exception;

}