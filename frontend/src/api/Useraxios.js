import http from '../http-common'

const mypage = (data, success, error) => {
    http
        .get('/api/user/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const login = (data, success, error) => {
    http
        .post('/api/user/login/', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const insertUser = (data, success, error) => {
    http
        .post('/api/user/user/', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}


const deleteUser = (data, success, error) => {
    http
        .delete('/api/user/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const updateUser = (data, success, error) => {
    http
        .put('/api/user/', data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const duplicateNickname = (data, success, error) => {
    http
        .get('/api/nicknamecheck/' + data)
        .then((res) => {
            success(res)
        })
        .catch((err) => {
            error(err)
        })
}

const axiosFunction = {
    mypage: (data, success, error) => mypage(data, success, error),
    login: (data, success, error) => login(data, success, error),
    insertUser: (data, success, error) => insertUser(data, success, error),
    deleteUser: (data, success, error) => deleteUser(data, success, error),
    updateUser: (data, success, error) => updateUser(data, success, error),
    duplicateNickname: (data, success, error) => duplicateNickname(data, success, error),
}
export default axiosFunction