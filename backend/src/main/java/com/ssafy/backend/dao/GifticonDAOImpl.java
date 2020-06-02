package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.Gifticon;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GifticonDAOImpl implements GifticonDAO {

    private String ns = "GifticonMapper.";
    @Autowired
    private SqlSession sqlSession;
    
    @Override
    public List<Gifticon> getAllGifticon() throws Exception {
        return sqlSession.selectList(ns+"selectAllGifticon");
    }

    @Override
    public Gifticon getGifticonById(int id) throws Exception {
        return sqlSession.selectOne(ns+"selectGifticon");
    }

    @Override
    public void insertGifticon(Gifticon gifticon) throws Exception {
        sqlSession.insert(ns+"insertGifticon",gifticon);
    }

    @Override
    public void deleteGifticon(int id) throws Exception {
        sqlSession.delete(ns+"deleteGifticon",id);
    }

    @Override
    public void updateGifticon(Gifticon gifticon) throws Exception {
        sqlSession.update(ns+"updateGifticon",gifticon);
    }
    
}