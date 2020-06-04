import http from '../http-common'

const getRecipePopularOrdered = (success, error) => {
    http
        .get('api/recipe_popular')
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const getRecipeRecentOrdered = (success, error) => {
    http
        .get('api/recipe_recent')
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const getRecipeBySearch = (data, success, error) => {
    http
        .get('api/recipe?keyword=' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const getRecipeById = (data, success, error) => {
    http
        .get('api/recipe/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const insertRecipe = (data, success, error) => {
    http
        .post('/api/recipe', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const updateRecipe = (data, success, error) => {
    http
        .put('/api/recipe', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const deleteRecipe = (data, success, error) => {
    http
        .delete('/api/recipe/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const getProductAutoComplete = (data, success, error) => {
    http
        .get('/api/autocomplete?keyword=' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const insertRecipeComment = (data, success, error) => {
    http
        .post('/api/recipe_comment', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const deleteRecipeComment = (data, success, error) => {
    http
        .delete('/api/recipe_comment/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const getComment = (data, success, error) => {
    http
        .get('/api/recipe_comment/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const uploadFiles = (data, success, error) => {
    http
        .post('/api/uploadFiles', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}


const axiosFunction = {
    getRecipePopularOrdered: (data, success, error) => getRecipePopularOrdered(data, success, error),
    getRecipeRecentOrdered: (data, success, error) => getRecipeRecentOrdered(data, success, error),
    getRecipeBySearch: (data, success, error) => getRecipeBySearch(data, success, error),
    getRecipeById: (data, success, error) => getRecipeById(data, success, error),
    insertRecipe: (data, success, error) => insertRecipe(data, success, error),
    updateRecipe: (data, success, error) => updateRecipe(data, success, error),
    deleteRecipe: (data, success, error) => deleteRecipe(data, success, error),
    getProductAutoComplete: (data, success, error) => getProductAutoComplete(data, success, error),
    insertRecipeComment: (data, success, error) => insertRecipeComment(data, success, error),
    deleteRecipeComment: (data, success, error) => deleteRecipeComment(data, success, error),
    getComment: (data, success, error) => getComment(data, success, error),
    uploadFiles: (data, success, error) => uploadFiles(data, success, error)
}

export default axiosFunction