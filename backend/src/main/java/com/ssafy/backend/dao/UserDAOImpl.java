package com.ssafy.backend.dao;

import com.ssafy.backend.vo.User;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private String ns = "UserMapper.";
    private SqlSession sqlSession;

    @Override
    public void deleteUser(String userId) throws Exception {
        sqlSession.delete(ns+"deleteUser", userId);

    }

    @Override
    public void insertUser(User user) throws Exception {
        sqlSession.insert(ns+"insertUser",user);
    }

    @Override
    public User login(User user) throws Exception {
        return sqlSession.selectOne(ns+"login",user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        sqlSession.update(ns+"updateUser",user);
    }
    
}