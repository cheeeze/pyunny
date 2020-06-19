package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.Favorite;
import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.Recipe;
import com.ssafy.backend.vo.RecipeComment;
import com.ssafy.backend.vo.User;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private String ns = "UserMapper.";
    @Autowired
    private SqlSession sqlSession;

    @Override
    public void deleteUser(String userId) throws Exception {
        sqlSession.delete(ns + "deleteUser", userId);

    }

    @Override
    public void insertUser(User user) throws Exception {
        sqlSession.insert(ns + "insertUser", user);
    }

    @Override
    public User login(User user) throws Exception {
        return sqlSession.selectOne(ns + "login", user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        sqlSession.update(ns + "updateUser", user);
    }

    @Override
    public int duplicateNickname(String nickname) throws Exception {
        return sqlSession.selectOne(ns + "duplicateNickname", nickname);
    }

    @Override
    public User selectOne(int id) throws Exception {
        return sqlSession.selectOne(ns + "selectOne", id);
    }

    @Override
    public List<Recipe> getUserRecipe(int id) {
        return sqlSession.selectList(ns + "getUserRecipe", id);
    }

    @Override
    public List<Recipe> getUserRecipeLike(int id) {
        return sqlSession.selectList(ns + "getUserRecipeLike", id);
    }

    @Override
    public List<RecipeComment> getUserRecipeComment(int id) {
        return sqlSession.selectList(ns + "getUserRecipeComment", id);
    }

    @Override
    public List<Product> getUserFavorite(int id) {
        return sqlSession.selectList(ns + "getUserFavorite", id);
    }

    @Override
    public void deleteUserFavorite(Favorite f) {
        sqlSession.delete(ns + "deleteUserFavorite", f);

    }
}