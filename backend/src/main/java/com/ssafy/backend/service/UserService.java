package com.ssafy.backend.service;

import com.ssafy.backend.vo.User;

public interface UserService {
    void insertUser(User user) throws Exception;
    void updateUser(User user) throws Exception;
    void deleteUser(String userId) throws Exception;
    User login(User user) throws Exception;
}