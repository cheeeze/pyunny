import http from '../http-common'

const getStoreNear = (data, success, error) => {
    http
        .get('/api/store', {
            params: {
                latitude: data.latitude,
                longitude: data.longitude,
                distance: data.distance,
                keyword: data.keyword,
                store: data.store,
            }
        })
        .then((res) => {
            success(res);
        })
        .catch((err) => {
            error(err)
        })
}

const getStoreProductBySearch = (data, success, error) => {
    http
        .get('/api/store_product', {
            params: {
                latitude: data.latitude,
                longitude: data.longitude,
                distance: data.distance,
                keyword: data.keyword,
                store: data.store,
            }
        })
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const axiosFunction = {
    getStoreNear: (data, success, error) => getStoreNear(data, success, error),
    getStoreProductBySearch: (data, success, error) => getStoreProductBySearch(data, success, error)
}

export default axiosFunction