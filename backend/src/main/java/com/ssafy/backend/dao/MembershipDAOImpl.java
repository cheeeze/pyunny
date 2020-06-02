package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.Membership;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {

    private String ns = "MembershipMapper.";
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Membership> getAllMembership() throws Exception {
        return sqlSession.selectList(ns+"selectAllMembership");
    }

    @Override
    public Membership getMembershipById(int id) throws Exception {
        return sqlSession.selectOne(ns+"selectMembership");
    }

    @Override
    public void insertMembership(Membership membership) throws Exception {
        sqlSession.insert(ns+"insertMembership", membership);
    }

    @Override
    public void updateMembership(Membership membership) throws Exception {
        sqlSession.update(ns+"updateMembership", membership);
    }

    @Override
    public void deleteMembership(int id) throws Exception {
        sqlSession.delete(ns+"deleteMembership",id);
    }
    
}