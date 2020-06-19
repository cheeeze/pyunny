package com.ssafy.backend.dao;

import java.util.List;

import com.ssafy.backend.vo.Favorite;
import com.ssafy.backend.vo.Product;
import com.ssafy.backend.vo.Recipe;
import com.ssafy.backend.vo.RecipeComment;
import com.ssafy.backend.vo.User;

public interface UserDAO {
    User selectOne(int id) throws Exception;

    void insertUser(User user) throws Exception;

    void updateUser(User user) throws Exception;

    void deleteUser(String userId) throws Exception;

    User login(User user) throws Exception;

    int duplicateNickname(String nickname) throws Exception;

    List<Recipe> getUserRecipe(int id);

    List<Recipe> getUserRecipeLike(int id);

    List<RecipeComment> getUserRecipeComment(int id);

    List<Product> getUserFavorite(int id);

    void deleteUserFavorite(Favorite f);
}