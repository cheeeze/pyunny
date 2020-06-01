package com.ssafy.backend.service;

import com.ssafy.backend.dao.UserDAO;
import com.ssafy.backend.vo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;

    @Override
    public void deleteUser(String userId) throws Exception {
        dao.deleteUser(userId);
    }

    @Override
    public void insertUser(User user) throws Exception {
        dao.insertUser(user);
    }

    @Override
    public User login(User user) throws Exception {
        return dao.login(user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        dao.updateUser(user);
    }

    @Override
    public int duplicateNickname(String nickname) throws Exception {
        return dao.duplicateNickname(nickname);
    }

    @Override
    public User selectOne(int id) throws Exception {
        return dao.selectOne(id);
    }
}