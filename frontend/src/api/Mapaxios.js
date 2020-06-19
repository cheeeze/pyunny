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

const getDeliveryStoreNear = (data, success, error) => {
    http
        .get('/api/store/delivery', {
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


const getAtmStoreNear = (data, success, error) => {
    http
        .get('/api/store/atm', {
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

const getMedicineStoreNear = (data, success, error) => {
    http
        .get('/api/store/medicine', {
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

const get24hourStoreNear = (data, success, error) => {
    http
        .get('/api/store/24hour', {
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

const getStockByStoreId = (data, success, error) => {
    http
        .get('/api/store_product/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })

}

const axiosFunction = {
    getStoreNear: (data, success, error) => getStoreNear(data, success, error),
    getAtmStoreNear: (data, success, error) => getAtmStoreNear(data, success, error),
    getMedicineStoreNear: (data, success, error) => getMedicineStoreNear(data, success, error),
    getDeliveryStoreNear: (data, success, error) => getDeliveryStoreNear(data, success, error),
    get24hourStoreNear: (data, success, error) => get24hourStoreNear(data, success, error),
    getStoreProductBySearch: (data, success, error) => getStoreProductBySearch(data, success, error),
    getStockByStoreId: (data, success, error) => getStockByStoreId(data, success, error),
}

export default axiosFunction