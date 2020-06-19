package com.ssafy.backend.service;

import java.util.List;

import com.ssafy.backend.dao.UserDAO;
import com.ssafy.backend.vo.Favorite;
import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.Recipe;
import com.ssafy.backend.vo.RecipeComment;
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

    @Override
    public List<Recipe> getUserRecipe(int id) {
        return dao.getUserRecipe(id);
    }

    @Override
    public List<Recipe> getUserRecipeLike(int id) {
        return dao.getUserRecipeLike(id);
    }

    @Override
    public List<RecipeComment> getUserRecipeComment(int id) {
        return dao.getUserRecipeComment(id);
    }

    @Override
    public List<Product> getUserFavorite(int id) {
        return dao.getUserFavorite(id);
    }

    @Override
    public void deleteUserFavorite(Favorite f) {
        dao.deleteUserFavorite(f);

    }
}