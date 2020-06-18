import axios from 'axios'

export default axios.create({
    // baseURL: 'http://127.0.0.1:8080',
    baseURL: 'http://k02d1021.p.ssafy.io:8080',

    headers: {
        'Content-type': 'application/json; charset=utf-8',
    },
})