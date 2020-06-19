import http from '../http-common'

const mypage = (data, success, error) => {
    http
        .get('/api/user/' + data)
        .then(res => {
            success(res)
        })
        .catch(err => {
            error(err)
        })
}

const login = (data, success, error) => {
    http
        .post('/api/user/login/', data)
        .then(res => {
            success(res)
        })
        .catch(err => {
            error(err)
        })
}

const insertUser = (data, success, error) => {
    http
        .post('/api/user/user/', data)
        .then(res => {
            success(res)
        })
        .catch(err => {
            error(err)
        })
}

const deleteUser = (data, success, error) => {
    http
        .delete('/api/user/' + data)
        .then(res => {
            success(res)
        })
        .catch(err => {
            error(err)
        })
}

const updateUser = (data, success, error) => {
    http
        .put('/api/user/', data)
        .then(res => {
            success(res)
        })
        .catch(err => {
            error(err)
        })
}

const duplicateNickname = (data, success, error) => {
    http
        .get('/api/user/nicknamecheck/' + data)
        .then(res => {
            success(res)
        })
        .catch(err => {
            error(err)
        })
}

const userRecipe = (data, success, error) => {
    http
        .get('/api/user/recipe/' + data)
        .then(res => {
            success(res)
        })
        .catch(err => {
            error(err)
        })
}


const userRecipeComment = (data, success, error) => {
    http
        .get('/api/user/recipe_comment/' + data)
        .then(res => {
            success(res)
        })
        .catch(err => {
            error(err)
        })
}


const userRecipeLike = (data, success, error) => {
    http
        .get('/api/user/recipe_like/' + data)
        .then(res => {
            success(res)
        })
        .catch(err => {
            error(err)
        })
}

const userFavorite = (data, success, error) => {
    http
        .get('/api/user/favorite/' + data)
        .then(res => {
            success(res)
        })
        .catch(err => {
            error(err)
        })
}

const deleteuserFavorite = (data, success, error) => {
    http
        .delete('/api/user/favorite?userId=' + data.userId + '&recipeId=' + data.recipeId)
        .then(res => {
            success(res)
        })
        .catch(err => {
            error(err)
        })
}


const axiosFunction = {
    mypage: (data, success, error) => mypage(data, success, error),
    login: (data, success, error) => login(data, success, error),
    insertUser: (data, success, error) => insertUser(data, success, error),
    deleteUser: (data, success, error) => deleteUser(data, success, error),
    updateUser: (data, success, error) => updateUser(data, success, error),
    duplicateNickname: (data, success, error) =>
        duplicateNickname(data, success, error),
    userRecipe: (data, success, error) => userRecipe(data, success, error),
    userRecipeComment: (data, success, error) => userRecipeComment(data, success, error),
    userRecipeLike: (data, success, error) => userRecipeLike(data, success, error),
    userFavorite: (data, success, error) => userFavorite(data, success, error),
    deleteuserFavorite: (data, success, error) => deleteuserFavorite(data, success, error),

}
export default axiosFunction