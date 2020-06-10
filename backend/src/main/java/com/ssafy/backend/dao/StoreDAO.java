package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.MapSearch;
import com.ssafy.backend.vo.MapSearchResult;
import com.ssafy.backend.vo.Store;
import com.ssafy.backend.vo.StoreProduct;

public interface StoreDAO {
    List<Store> getStoreNear(MapSearch search) throws Exception;

    List<Store> getAtmStoreNear(MapSearch search) throws Exception;

    List<Store> getMedicineStoreNear(MapSearch search) throws Exception;

    List<Store> getDeliveryStoreNear(MapSearch search) throws Exception;

    List<Store> get24hourStoreNear(MapSearch search) throws Exception;

    List<MapSearchResult> getStoreProductBySearch(MapSearch search) throws Exception;

    List<StoreProduct> getStoreProductById(int storeId) throws Exception;
}