import http from '../http-common'
import axios from 'axios'


/* 상품 추가 */
const insertProduct = (data, success, error) => {
    http
        .post('/api/product', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}


/* 할인 추가 */
const insertSale = (data, success, error) => {
    http
        .post('/api/sale', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* 상품 다 가져오기 */
const getProduct = (success, error) => {
    http
        .get('/api/product')
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* 할인 다 가져오기 */
const getSale = (success, error) => {
    http
        .get('/api/sale')
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* ID로 상품 가져오기 */
const getProductById = (data, success, error) => {
    http
        .get('/api/product/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* ID로 할인 가져오기 */
const getSaleById = (data, success, error) => {
    http
        .get('/api/sale/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* 이름으로 상품 가져오기 */
const getProductByName = (data, success, error) => {
    http
        .get('/api/product/name?keyword=' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* 이름으로 할인 가쟈오기 */
const getSaleByName = (data, success, error) => {
    http
        .get('/api/sale/name?keyword=' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* 검색으로 가져오기 */
const getProductBySearch = (data, success, error) => {
    http
        .get('/api/product/saerch', {
            params: {
                franchise: data.franchise,
                keyword: this.keyword
            }
        })
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const getSaleBySearch = (data, success, error) => {
    http
        .get('/api/sale/saerch', {
            params: {
                franchise: data.franchise,
                keyword: this.keyword
            }
        })
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}


const updateProduct = (data, success, error) => {
    http
        .put('/api/product', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const updateSale = (data, success, error) => {
    http
        .put('/api/sale', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const deleteProduct = (data, success, error) => {
    http
        .delete('/api/product/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const deleteSale = (data, success, error) => {
    http
        .delete('/api/sale/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* 관심품목 추가 */
const insertFavorite = (data, success, error) => {
    http
        .post('/api/favorite', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* 관심품목 삭제 */
const deleteFavorite = (data, success, error) => {
    http
        .delete('/api/favorite/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* 평가 하기 */
const insertRating = (data, success, error) => {
    http
        .post('/api/rating', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const deleteRating = (data, success, error) => {
    http
        .delete('/api/rating/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* 평가 수정 */
const updateRating = (data, success, error) => {
    http
        .put('/api/rating', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* 상품 한줄평 작성 */
const insertComment = (data, success, error) => {
    http
        .post('/api/product_comment', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

/* 상품에 대한 한줄평들 가져오기 */
const getCommentById = (data, success, error) => {
    http
        .get('/api/product_comment/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const updateComment = (data, success, error) => {
    http
        .put('/api/comment/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const deleteComment = (data, success, error) => {
    http
        .delete('/api/comment/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}




const axiosFunction = {
    insertProduct: (data, success, error) => insertProduct(data, success, error),
    insertSale: (data, success, error) => insertSale(data, success, error),
    getProduct: (data, success, error) => getProduct(data, success, error),
    getSale: (data, success, error) => getSale(data, success, error),
    getProductById: (data, success, error) => getProductById(data, success, error),
    getSaleById: (data, success, error) => getSaleById(data, success, error),
    getProductByName: (data, success, error) => getProductByName(data, success, error),
    getSaleByName: (data, success, error) => getSaleByName(data, success, error),
    getProductBySearch: (data, success, error) => getProductBySearch(data, success, error),
    getSaleBySearch: (data, success, error) => getSaleBySearch(data, success, error),
    updateSale: (data, success, error) => updateSale(data, success, error),
    updateProduct: (data, success, error) => updateProduct(data, success, error),
    deleteProduct: (data, success, error) => deleteProduct(data, success, error),
    deleteSale: (data, success, error) => deleteSale(data, success, error),
    insertFavorite: (data, success, error) => insertFavorite(data, success, error),
    deleteFavorite: (data, success, error) => deleteFavorite(data, success, error),
    insertRating: (data, success, error) => insertRating(data, success, error),
    deleteRating: (data, success, error) => deleteRating(data, success, error),
    updateRating: (data, success, error) => updateRating(data, success, error),
    insertComment: (data, success, error) => insertComment(data, success, error),
    getCommentById: (data, success, error) => getCommentById(data, success, error),
    updateComment: (data, success, error) => updateComment(data, success, error),
    deleteComment: (data, success, error) => deleteComment(data, success, error),
}

export default axiosFunction