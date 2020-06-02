package com.ssafy.backend.service;

import java.util.List;

import com.ssafy.backend.dao.GifticonDAO;
import com.ssafy.backend.vo.Gifticon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GifticonServiceImpl implements GifticonService {

    @Autowired
    private GifticonDAO dao;

    @Override
    public List<Gifticon> getAllGifticon() throws Exception {
        return dao.getAllGifticon();
    }

    @Override
    public Gifticon getGifticonById(int id) throws Exception {
        return dao.getGifticonById(id);
    }

    @Override
    public void insertGifticon(Gifticon gifticon) throws Exception {
        dao.insertGifticon(gifticon);
    }

    @Override
    public void deleteGifticon(int id) throws Exception {
        dao.deleteGifticon(id);
    }

    @Override
    public void updateGifticon(Gifticon gifticon) throws Exception {
        dao.updateGifticon(gifticon);
    }
    
}