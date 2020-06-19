package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.GiftInsert;
import com.ssafy.backend.vo.Gifticon;

public interface GifticonDAO {
    List<Gifticon> getAllGifticon() throws Exception;

    List<Gifticon> getGifticonUserId(int id);

    List<Gifticon> getUsedGifticonUserId(int id);

    Gifticon getGifticonById(int id) throws Exception;

    void insertGifticon(GiftInsert gifticon) throws Exception;

    void deleteGifticon(List<Integer> id) throws Exception;

    void updateGifticon(List<Integer> id) throws Exception;
}