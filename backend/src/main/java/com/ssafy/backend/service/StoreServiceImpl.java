package com.ssafy.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ssafy.backend.dao.StoreDAO;
import com.ssafy.backend.vo.MapSearch;
import com.ssafy.backend.vo.Store;
import com.ssafy.backend.vo.StoreProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDAO dao;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Store> getStoreNear(MapSearch search) throws Exception {

        ListOperations<String, Object> vop = redisTemplate.opsForList();

        String key = search.getLatitude() + "," + search.getLongitude();
        int size = (int) (long) vop.size(key);

        if (size != 0) { // 없는 key면 0이라 나온다
            System.out.println("있는 key");

            List<Store> res = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                res.add((Store) vop.index(key, i));
            }
            return res;
        } else {
            System.out.println("없는 key");
            List<Store> res = dao.getStoreNear(search);

            for (Store s : res) {
                System.out.println(s.toString());
                vop.rightPush(key, s);
            }
            redisTemplate.expire(key, 10, TimeUnit.MINUTES);
            return res;
        }
    }

    @Override
    public List<StoreProduct> getStoreProductById(MapSearch search) throws Exception {
        // near Store
        List<Store> storeList = getStoreNear(search);
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < storeList.size(); i++) {
            idList.add(storeList.get(i).getId());
        }
        search.setStore(idList);

        return dao.getStoreProductById(search);
    }

}