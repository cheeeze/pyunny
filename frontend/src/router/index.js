import Vue from 'vue'
import Router from 'vue-router'
//import axios from '../api/axiosScript.js'
// Routes
import paths from './paths'


function route(path, view, name) {
    return {
        name: name || view,
        path,
        component: (resolve) => import(`@/views/${view}.vue`).then(resolve),
        props: true
    }
}

Vue.use(Router)

// Create a new router
const router = new Router({
    mode: 'history',
    routes: paths
        .map((path) => route(path.path, path.view, path.name))
        .concat([{
            path: '*',
            redirect: '/',
        }, ]),
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition
        }
        if (to.hash) {
            return {
                selector: to.hash,
            }
        }
        return {
            x: 0,
            y: 0,
        }
    },
})

/* router.beforeEach((to, from, next) => {
    let data = sessionStorage.getItem('session')
    if (data != null) {
        axios.sessionCheck(
            data,
            (res) => {
                sessionStorage.setItem('session', JSON.stringify(res.data.session))
                next()
            },
            (err) => {
                sessionStorage.removeItem('session')
                session.state.login = false
                next('/')
            },
        )
    }
    next()
})*/
export default router