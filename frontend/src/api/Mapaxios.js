import http from '../http-common'
import axios from 'axios'

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

const getStoreProductById = (data, success, error) => {
    http
        .get('/api/store_proeuct', {
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
    getStoreProductById: (data, success, error) => getStoreProductById(data, success, error)
}

export default axiosFunction