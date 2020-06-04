package com.ssafy.backend.dao;

import com.ssafy.backend.vo.User;

public interface UserDAO {
    User selectOne(int id) throws Exception;
    void insertUser(User user) throws Exception;
    void updateUser(User user) throws Exception;
    void deleteUser(String userId) throws Exception;
    User login(User user) throws Exception;
    int duplicateNickname(String nickname) throws Exception;
}