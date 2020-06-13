import http from '../http-common'

const getAllGifticon = (data, success, error) => {
    http
        .get('/api/gift/all')
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const getGifticonById = (data, success, error) => {
    http
        .get('/api/gift/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const insertGifticon = (data, success, error) => {
    http
        .post('/api/gift/gift', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const deleteGifticon = (data, success, error) => {
    http
        .delete('/api/gift/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const updateGifticon = (data, success, error) => {
    http
        .put('/api/gift/gist', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const getAllMembership = (success, error) => {
    http
        .get('/api/membership/all')
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const getMembershipById = (data, success, error) => {
    http
        .get('/api/membership/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const insertMembership = (data, success, error) => {
    http
        .post('/api/membership/', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const updateMembership = (data, success, error) => {
    http
        .put('/api/membership/', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}


const deleteMembership = (data, success, error) => {
    http
        .delete('/api/membership/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}


const getMembershipByUserId = (data, success, error) => {
    http
        .get('/api/membership/user/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}


const axiosFunction = {
    getAllGifticon: (success, error) => getAllGifticon(success, error),
    getGifticonById: (data, success, error) => getGifticonById(data, success, error),
    insertGifticon: (data, success, error) => insertGifticon(data, success, error),
    deleteGifticon: (data, success, error) => deleteGifticon(data, success, error),
    updateGifticon: (data, success, error) => updateGifticon(data, success, error),
    getAllMembership: (success, error) => getAllMembership(success, error),
    getMembershipById: (data, success, error) => getMembershipById(data, success, error),
    insertMembership: (data, success, error) => insertMembership(data, success, error),
    updateMembership: (data, success, error) => updateMembership(data, success, error),
    deleteMembership: (data, success, error) => deleteMembership(data, success, error),
    getMembershipByUserId: (data, success, error) => getMembershipByUserId(data, success, error),

}
export default axiosFunction