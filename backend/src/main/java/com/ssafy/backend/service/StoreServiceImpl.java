package com.ssafy.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ssafy.backend.dao.StoreDAO;
import com.ssafy.backend.vo.MapSearch;
import com.ssafy.backend.vo.MapSearchResult;
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
                // System.out.println(s.toString());
                vop.rightPush(key, s);
            }
            redisTemplate.expire(key, 10, TimeUnit.MINUTES);
            return res;
        }
    }

    @Override
    public List<MapSearchResult> getStoreProductBySearch(MapSearch search) throws Exception {
        // near Store
        List<Store> storeList = getStoreNear(search);
        if (storeList.size() == 0) {
            return new ArrayList<MapSearchResult>();
        }
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i < storeList.size(); i++) {
            idList.add(storeList.get(i).getId());
        }
        search.setStore(idList);

        return dao.getStoreProductBySearch(search);
    }

    @Override
    public List<Store> getAtmStoreNear(MapSearch search) throws Exception {
        ListOperations<String, Object> vop = redisTemplate.opsForList();

        String key = search.getLatitude() + "," + search.getLongitude();
        int size = (int) (long) vop.size(key);

        if (size != 0) { // 없는 key면 0이라 나온다
            System.out.println("있는 key atm");

            List<Store> res = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (((Store) vop.index(key, i)).getIsatm() == 1) {
                    res.add((Store) vop.index(key, i));
                }
            }
            return res;
        } else {
            System.out.println("없는 key atm");
            List<Store> res = dao.getAtmStoreNear(search);
            return res;
        }
    }

    @Override
    public List<Store> getMedicineStoreNear(MapSearch search) throws Exception {
        ListOperations<String, Object> vop = redisTemplate.opsForList();

        String key = search.getLatitude() + "," + search.getLongitude();
        int size = (int) (long) vop.size(key);

        if (size != 0) { // 없는 key면 0이라 나온다
            System.out.println("있는 key medicine");

            List<Store> res = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (((Store) vop.index(key, i)).getIsmedicine() == 1) {
                    res.add((Store) vop.index(key, i));
                }
            }
            return res;
        } else {
            System.out.println("없는 key medicine");
            List<Store> res = dao.getMedicineStoreNear(search);
            return res;
        }
    }

    @Override
    public List<Store> getDeliveryStoreNear(MapSearch search) throws Exception {
        ListOperations<String, Object> vop = redisTemplate.opsForList();

        String key = search.getLatitude() + "," + search.getLongitude();
        int size = (int) (long) vop.size(key);

        if (size != 0) { // 없는 key면 0이라 나온다
            System.out.println("있는 key delivery");

            List<Store> res = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (((Store) vop.index(key, i)).getIsdelivery() == 1) {
                    res.add((Store) vop.index(key, i));
                }
            }
            return res;
        } else {
            System.out.println("없는 key delivery");
            List<Store> res = dao.getDeliveryStoreNear(search);
            return res;
        }
    }

    @Override
    public List<Store> get24hourStoreNear(MapSearch search) throws Exception {
        ListOperations<String, Object> vop = redisTemplate.opsForList();

        String key = search.getLatitude() + "," + search.getLongitude();
        int size = (int) (long) vop.size(key);

        if (size != 0) { // 없는 key면 0이라 나온다
            System.out.println("있는 key fulltime");

            List<Store> res = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (((Store) vop.index(key, i)).getIsfulltime() == 1) {
                    res.add((Store) vop.index(key, i));
                }
            }
            return res;
        } else {
            System.out.println("없는 key fulltime");
            List<Store> res = dao.get24hourStoreNear(search);
            return res;
        }
    }

    @Override
    public List<StoreProduct> getStoreProductById(int storeId) throws Exception {
        return dao.getStoreProductById(storeId);
    }

}