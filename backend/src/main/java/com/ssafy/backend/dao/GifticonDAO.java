package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.Gifticon;

public interface GifticonDAO {
    List<Gifticon> getAllGifticon() throws Exception;
    Gifticon getGifticonById(int id) throws Exception;
    void insertGifticon(Gifticon gifticon) throws Exception;
    void deleteGifticon(int id) throws Exception;
    void updateGifticon(Gifticon gifticon) throws Exception;
}